package com.cxy.shardingjdbc.sharding;

import com.google.common.collect.Range;
import io.shardingjdbc.core.api.algorithm.sharding.RangeShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.RangeShardingAlgorithm;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

public class DatabaseDateRangeAlgorithm implements RangeShardingAlgorithm<Date> {

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Date> rangeShardingValue) {
        System.out.println("==================== database range");
        Collection<String> result = new LinkedHashSet<>(collection.size());
        Range<Date> range = rangeShardingValue.getValueRange();
        Date upperEndpoint = range.upperEndpoint();
        for (Date i = range.lowerEndpoint(); i.before(upperEndpoint);) {
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

    private String getYear(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        return calendar.get(Calendar.YEAR) + "";
    }
    private Date addYear(Date time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.YEAR, 1);
        return calendar.getTime();
    }
}
