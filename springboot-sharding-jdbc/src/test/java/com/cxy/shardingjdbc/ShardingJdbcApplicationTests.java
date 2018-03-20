package com.cxy.shardingjdbc;

import com.cxy.shardingjdbc.entity.Order;
import com.cxy.shardingjdbc.entity.OrderItem;
import com.cxy.shardingjdbc.mapper.sharding.OrderItemMapper;
import com.cxy.shardingjdbc.mapper.sharding.OrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
        List<Long> orderIds = new ArrayList<>(10);

        Order order = new Order(1, 1521428895);
        orderRepository.insert(order);
        long orderId = order.getOrderId();
        orderIds.add(orderId);
        OrderItem item = new OrderItem(orderId, 1, 1521428895);
        orderItemRepository.insert(item);

        order = new Order(1, 1524107295);
        orderRepository.insert(order);
        orderId = order.getOrderId();
        orderIds.add(orderId);
        item = new OrderItem(orderId, 1, 1524107295);
        orderItemRepository.insert(item);

        order = new Order(1, 1534648095);
        orderRepository.insert(order);
        orderId = order.getOrderId();
        orderIds.add(orderId);
        item = new OrderItem(orderId, 1, 1534648095);
        orderItemRepository.insert(item);

        order = new Order(1, 1542596895);
        orderRepository.insert(order);
        orderId = order.getOrderId();
        orderIds.add(orderId);
        item = new OrderItem(orderId, 1, 1542596895);
        orderItemRepository.insert(item);

        System.out.println("=================" + orderIds);
        System.out.println(orderItemRepository.selectAll());
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

}
