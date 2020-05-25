package com.cxy.sphere.sharding;

import com.google.common.collect.Range;
import io.shardingsphere.core.api.algorithm.sharding.RangeShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.RangeShardingAlgorithm;

import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedHashSet;

public class DatabaseRangeAlgorithm implements RangeShardingAlgorithm<Integer> {

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Integer> rangeShardingValue) {
        System.out.println("==================== database range");
        Collection<String> result = new LinkedHashSet<>(collection.size());
        Range<Integer> range = rangeShardingValue.getValueRange();

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

    private int getYear(int time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time * 1000L);
        return calendar.get(Calendar.YEAR);
    }
}
