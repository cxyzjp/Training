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
        Collection<String> result = new LinkedHashSet<>(collection.size());
        Range<Integer> range = rangeShardingValue.getValueRange();
        Integer upperEndpoint = range.upperEndpoint();
        for (Integer i = range.lowerEndpoint(); i <= upperEndpoint;) {
            String season = getSeason(i);
            for (String each : collection) {
                if (each.endsWith(season)) {
                    result.add(each);
                }
            }
            i = addSeason(i);
        }
        // 循环最后一个点
        String season = getSeason(upperEndpoint);
        for (String each : collection) {
            if (each.endsWith(season)) {
                result.add(each);
            }
        }
        System.out.println("table names: " + result);
        return result;
    }

    private String getSeason(int time) {
        return OrderTablePreciseAlgorithm.getSeason(time);
    }

    private int addSeason(int time){
        return time + 7689600;
    }
}
