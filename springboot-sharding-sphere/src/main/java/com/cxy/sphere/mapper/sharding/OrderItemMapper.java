package com.cxy.sphere.mapper.sharding;

import com.cxy.sphere.entity.OrderItem;
import com.cxy.sphere.entity.OrderQuery;
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
