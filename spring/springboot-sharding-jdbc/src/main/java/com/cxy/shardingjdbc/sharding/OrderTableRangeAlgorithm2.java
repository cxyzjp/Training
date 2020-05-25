package com.cxy.shardingjdbc.sharding;

import com.google.common.collect.Range;
import io.shardingjdbc.core.api.algorithm.sharding.RangeShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.RangeShardingAlgorithm;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

/**
 * 按季度分表。表名类似t_order_2018_{1..4}
 */
public class OrderTableRangeAlgorithm2 implements RangeShardingAlgorithm<Integer> {

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Integer> rangeShardingValue) {
        System.out.println("==================== table range");
        Collection<String> result = new LinkedHashSet<>(collection.size());
        Range<Integer> range = rangeShardingValue.getValueRange();

        int lowerYearSeason = getYearSeason(range.lowerEndpoint());
        int upperYearSeason = getYearSeason(range.upperEndpoint());
        System.out.println(lowerYearSeason + " - " + upperYearSeason);

        for (int i = lowerYearSeason; i <= upperYearSeason; i++) {
            String year = String.valueOf(i).substring(0, 4);
            String season = String.valueOf(i).substring(4);
            String yearSeason = year + "_" + season;

            for (String each : collection) {
                if (each.endsWith(yearSeason))
                    result.add(each);
            }
            if ("4".equals(season))
                i = i + 10 - 4;
        }
        System.out.println("table names: " + result);
        return result;
    }

    private int getYearSeason(int time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time * 1000L);

        int result = calendar.get(Calendar.YEAR) * 10;
        int m = calendar.get(Calendar.MONTH) + 1;
        if (m < 4) {
            result += 1;
        } else if (m < 7) {
            result += 2;
        } else if (m < 10) {
            result += 3;
        } else {
            result += 4;
        }
        return result;
    }

}
