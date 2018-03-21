package com.cxy.shardingjdbc.sharding;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class DatabaseDatePreciseAlgorithm implements PreciseShardingAlgorithm<Date> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Date> preciseShardingValue) {
        System.out.println("==================== database precise");
        String defaultDS = "";
        for (String name : collection) {
            Date value = preciseShardingValue.getValue();
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

    private String getYear(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        return calendar.get(Calendar.YEAR) + "";
    }
}
