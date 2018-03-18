package com.cxy.shardingjdbc.sharding;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

public class OrderPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Integer>{

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue) {
        System.out.println("collection:" + collection.toString() + ",preciseShardingValue:" + preciseShardingValue.toString());
        for (String name : collection) {
            if (name.endsWith(preciseShardingValue.getValue() % collection.size() + "")) {
                System.out.println("return name:"+name);
                return name;
            }
        }
        return null;
    }
}
