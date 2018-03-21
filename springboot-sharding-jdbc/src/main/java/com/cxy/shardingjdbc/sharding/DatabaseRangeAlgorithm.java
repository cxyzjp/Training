package com.cxy.shardingjdbc.sharding;

import com.google.common.collect.Range;
import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.RangeShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import io.shardingjdbc.core.api.algorithm.sharding.standard.RangeShardingAlgorithm;

import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedHashSet;

public class DatabaseRangeAlgorithm implements RangeShardingAlgorithm<Integer> {

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Integer> rangeShardingValue) {
        System.out.println("==================== database range");
        System.out.println("collection:" + collection.toString() + ",rangeShardingValue:" + rangeShardingValue.toString());
        Collection<String> result = new LinkedHashSet<>(collection.size());
        Range<Integer> range = rangeShardingValue.getValueRange();
        for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : collection) {
                String year = getYear(i);
                if (each.endsWith(year)) {
                    System.out.println("database name: " + each);
                    result.add(each);
                }
            }
        }
        return result;
    }

    private String getYear(int time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time * 1000L);
        return calendar.get(Calendar.YEAR) + "";
    }
}
