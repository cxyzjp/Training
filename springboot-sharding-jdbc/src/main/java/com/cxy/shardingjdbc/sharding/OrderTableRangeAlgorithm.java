package com.cxy.shardingjdbc.sharding;

import com.google.common.collect.Range;
import io.shardingjdbc.core.api.algorithm.sharding.RangeShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.RangeShardingAlgorithm;

import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedHashSet;

public class OrderTableRangeAlgorithm implements RangeShardingAlgorithm<Integer> {

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Integer> rangeShardingValue) {
        System.out.println("==================== table range");
        System.out.println("collection:" + collection.toString() + ",rangeShardingValue:" + rangeShardingValue.toString());
        Collection<String> result = new LinkedHashSet<>(collection.size());
        Range<Integer> range = rangeShardingValue.getValueRange();
        for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : collection) {
                String season = getSeason(i);
                if (each.endsWith(season)) {
                    System.out.println("database name: " + each);
                    result.add(each);
                }
            }
        }
        return result;
    }

    private String getSeason(int time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time * 1000L);
        String result = calendar.get(Calendar.YEAR) + "_";

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
