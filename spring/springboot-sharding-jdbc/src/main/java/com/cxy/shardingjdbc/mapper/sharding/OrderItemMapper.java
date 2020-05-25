package com.cxy.shardingjdbc.mapper.sharding;

import com.cxy.shardingjdbc.entity.OrderItem;
import com.cxy.shardingjdbc.entity.OrderQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderItemMapper {

    void createIfNotExistsTable();

    void truncateTable();

    Long insert(OrderItem model);

    void delete(Long orderItemId);

    List<OrderItem> selectAll(OrderQuery query);

    void dropTable();
}
