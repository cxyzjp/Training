package com.cxy.sphere.sharding;

import io.shardingsphere.core.api.algorithm.sharding.ListShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.ShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.complex.ComplexKeysShardingAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * description:
 * author: bowen
 * date: 2018/8/15
 */
public class OrderComplexShardingAlgorithm implements ComplexKeysShardingAlgorithm {

    @Override
    public Collection<String> doSharding(Collection<String> collection, Collection<ShardingValue> shardingValues) {

        Collection<Long> orderIdValues = getShardingValue(shardingValues, "create_time");
        Collection<Long> userIdValues = getShardingValue(shardingValues, "buyer");
        List<String> shardingSuffix = new ArrayList<>();

        for (Long userIdVal : userIdValues) {
            for (Long orderIdVal : orderIdValues) {
                String suffix = userIdVal % 2 + "_" + orderIdVal % 2;
                collection.forEach(x -> {
                    if (x.endsWith(suffix)) {
                        shardingSuffix.add(x);
                    }
                });
            }
        }
        return shardingSuffix;
    }

    private Collection<Long> getShardingValue(Collection<ShardingValue> shardingValues, final String key) {
        Collection<Long> valueSet = new ArrayList<>();
        Iterator<ShardingValue> iterator = shardingValues.iterator();
        while (iterator.hasNext()) {
            ShardingValue next = iterator.next();
            if (next instanceof ListShardingValue) {
                ListShardingValue value = (ListShardingValue) next;
                if (value.getColumnName().equals(key)) {
                    return value.getValues();
                }
            }
        }
        return valueSet;
    }

}
