package com.cxy.shardingjdbc.sharding;

import com.google.common.collect.Range;
import io.shardingjdbc.core.api.algorithm.sharding.RangeShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.RangeShardingAlgorithm;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

public class DatabaseDateRangeAlgorithm implements RangeShardingAlgorithm<Date> {

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Date> rangeShardingValue) {
        System.out.println("==================== database range");
        Collection<String> result = new LinkedHashSet<>(collection.size());
        Range<Date> range = rangeShardingValue.getValueRange();

        Integer lowerYear = getYear(range.lowerEndpoint());
        Integer upperYear = getYear(range.upperEndpoint());
        for (Integer i = lowerYear; i <= upperYear; i++) {
            for (String each : collection) {
                if (each.endsWith(i + "")) {
                    result.add(each);
                }
            }
        }
        System.out.println("database names: " + result);
        return result;
    }

    private int getYear(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        return calendar.get(Calendar.YEAR);
    }
}
