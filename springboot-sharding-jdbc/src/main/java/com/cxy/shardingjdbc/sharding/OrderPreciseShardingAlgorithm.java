package com.cxy.shardingjdbc.sharding;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Calendar;
import java.util.Collection;

public class OrderPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Integer> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue) {
        System.out.println("collection:" + collection.toString() + ",preciseShardingValue:" + preciseShardingValue.toString());
        for (String name : collection) {
            int createTime = preciseShardingValue.getValue();
            String season = getSeason(createTime);
            if (name.endsWith(season)) {
                System.out.println("return name:" + name);
                return name;
            }
        }
        return null;
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
