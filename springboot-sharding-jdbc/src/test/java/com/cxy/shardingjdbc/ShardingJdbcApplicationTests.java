package com.cxy.shardingjdbc;

import com.cxy.shardingjdbc.entity.Order;
import com.cxy.shardingjdbc.entity.OrderItem;
import com.cxy.shardingjdbc.repository.OrderItemRepository;
import com.cxy.shardingjdbc.repository.OrderRepository;
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
    private OrderRepository orderRepository;
    @Resource
    private OrderItemRepository orderItemRepository;

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
        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setUserId(i);
            order.setStatus("INSERT_ORDER");
            order.setCreateTime(i / 4 + 1);
            orderRepository.insert(order);
            long orderId = order.getOrderId();
            orderIds.add(orderId);

            OrderItem item = new OrderItem();
            item.setOrderId(orderId);
            item.setUserId(i);
            item.setStatus("INSERT_TEST");
            item.setCreateTime(i / 4 + 1);
            orderItemRepository.insert(item);
        }
        System.out.println(orderIds);
        System.out.println(orderItemRepository.selectAll());
    }

    @Test
    public void drop() {
        orderItemRepository.dropTable();
        orderRepository.dropTable();
        System.out.println("drop success");
    }
}
