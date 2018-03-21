package com.cxy.shardingjdbc.sharding;

import com.google.common.collect.Range;
import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.RangeShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import io.shardingjdbc.core.api.algorithm.sharding.standard.RangeShardingAlgorithm;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

public class DatabaseRangeAlgorithm implements RangeShardingAlgorithm<Integer> {

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Integer> rangeShardingValue) {
        System.out.println("==================== database range");
        Collection<String> result = new LinkedHashSet<>(collection.size());
        Range<Integer> range = rangeShardingValue.getValueRange();
        Integer upperEndpoint = range.upperEndpoint();
        for (Integer i = range.lowerEndpoint(); i <= upperEndpoint;) {
            String year = getYear(i);
            for (String each : collection) {
                if (each.endsWith(year)) {
                    result.add(each);
                }
            }
            i = addYear(i);
        }
        // 循环最后一个点
        String year = getYear(upperEndpoint);
        for (String each : collection) {
            if (each.endsWith(year)) {
                result.add(each);
            }
        }
        System.out.println("database names: " + result);
        return result;
    }

    private String getYear(int time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time * 1000L);
        return calendar.get(Calendar.YEAR) + "";
    }
    private int addYear(int time){
        return time + 31536000;
    }
}
