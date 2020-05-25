package com.cxy.sphere.sharding;

import io.shardingsphere.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Calendar;
import java.util.Collection;

public class DatabasePreciseAlgorithm implements PreciseShardingAlgorithm<Integer> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue) {
        System.out.println("==================== database precise");
        String defaultDS = "";
        for (String name : collection) {
            int value = preciseShardingValue.getValue();
            String year = getYear(value);
            if (name.endsWith(year)) {
                System.out.println("database name: " + name);
                return name;
            }
            defaultDS = name;
        }
        return defaultDS;
//        throw new UnsupportedOperationException();
    }

    private String getYear(int time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time * 1000L);
        return calendar.get(Calendar.YEAR) + "";
    }

}
