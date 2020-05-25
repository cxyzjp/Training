package com.cxy.sphere.sharding;

import io.shardingsphere.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Calendar;
import java.util.Collection;

public class OrderTablePreciseAlgorithm implements PreciseShardingAlgorithm<Integer> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue) {
        System.out.println("==================== table precise");
        for (String name : collection) {
            int value = preciseShardingValue.getValue();
            String season = getSeason(value);
            if (name.endsWith(season)) {
                System.out.println("table name:" + name);
                return name;
            }
        }
        throw new UnsupportedOperationException();
    }

    static String getSeason(int time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time * 1000L);

        int m = calendar.get(Calendar.MONTH) + 1;
        String result;
        if (m < 4) {
            result = "1";
        } else if (m < 7) {
            result = "2";
        } else if (m < 10) {
            result = "3";
        } else {
            result = "4";
        }
        return result;
    }

}
