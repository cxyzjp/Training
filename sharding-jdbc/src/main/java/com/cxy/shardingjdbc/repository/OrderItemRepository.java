package com.cxy.shardingjdbc.repository;

import com.cxy.shardingjdbc.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderItemRepository {

    void createIfNotExistsTable();

    void truncateTable();

    Long insert(OrderItem model);

    void delete(Long orderItemId);

    List<OrderItem> selectAll();

    void dropTable();
}
