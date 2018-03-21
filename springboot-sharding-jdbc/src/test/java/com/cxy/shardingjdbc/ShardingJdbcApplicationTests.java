package com.cxy.shardingjdbc;

import com.cxy.shardingjdbc.entity.Order;
import com.cxy.shardingjdbc.entity.OrderItem;
import com.cxy.shardingjdbc.entity.OrderQuery;
import com.cxy.shardingjdbc.mapper.sharding.OrderItemMapper;
import com.cxy.shardingjdbc.mapper.sharding.OrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJdbcApplicationTests {

    @Resource
    private OrderMapper orderRepository;
    @Resource
    private OrderItemMapper orderItemRepository;

    @Test
    public void contextLoads() {
        System.out.println("hello world");
    }

    @Test
    public void create() {
        orderRepository.createIfNotExistsTable();
        orderItemRepository.createIfNotExistsTable();
        orderRepository.truncateTable();
        orderItemRepository.truncateTable();
        System.out.println("create success");
    }

    @Test
    public void insert() {
        Order order = new Order(1, 101, 1517875200, new Date(1517875200000L));
        orderRepository.insert(order);
        long id = order.getOrderId();
        OrderItem orderItem = new OrderItem(id, 1517875200, new Date(1517875200000L));
        orderItemRepository.insert(orderItem);
        orderItem = new OrderItem(id, 1517875200, new Date(1517875200000L));
        orderItemRepository.insert(orderItem);

        order = new Order(1, 101, 1524327556, new Date(1524327556000L));
        orderRepository.insert(order);
        id = order.getOrderId();
        orderItem = new OrderItem(id, 1524327556, new Date(1524327556000L));
        orderItemRepository.insert(orderItem);
        orderItem = new OrderItem(id, 1524327556, new Date(1524327556000L));
        orderItemRepository.insert(orderItem);
    }

    @Test
    public void truncate() {
        orderRepository.truncateTable();
        orderItemRepository.truncateTable();
        System.out.println("truncate success");
    }

    @Test
    public void drop() {
        orderItemRepository.dropTable();
        orderRepository.dropTable();
        System.out.println("drop success");
    }

    @Test
    public void select() {
        OrderQuery query = new OrderQuery();
//        query.setCreateTime(1);
        query.setBuyer(1);
        query.setcStart(1);
        query.setcEnd(1524327556);
//        query.setuStart(new Date(1517875200000L));
//        query.setuEnd(new Date(1524327556000L));
        System.out.println(orderRepository.selectAll(query));
    }

}
