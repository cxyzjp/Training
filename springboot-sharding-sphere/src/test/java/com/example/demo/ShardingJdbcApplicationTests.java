package com.example.demo;

import com.cxy.sphere.DemoApplication;
import com.cxy.sphere.entity.Order;
import com.cxy.sphere.entity.OrderItem;
import com.cxy.sphere.entity.OrderQuery;
import com.cxy.sphere.mapper.sharding.OrderItemMapper;
import com.cxy.sphere.mapper.sharding.OrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class ShardingJdbcApplicationTests {

    @Resource
    private OrderMapper orderRepository;
    @Resource
    private OrderItemMapper orderItemRepository;

    @Test
    public void contextLoads() {
        System.out.println("hello world");
    }

//    @Test
//    public void create() {
//        orderRepository.createIfNotExistsTable();
//        orderItemRepository.createIfNotExistsTable();
//        orderRepository.truncateTable();
//        orderItemRepository.truncateTable();
//        System.out.println("create success");
//    }

    @Test
    public void insert() {
        Order order = new Order(1, 102, 1517875200, new Date(1517875200000L));
//        orderRepository.insertSelective(order);
        long id = order.getOrderId();
        OrderItem orderItem = new OrderItem(id, 1517875200, new Date(1517875200000L));
        orderItemRepository.insert(orderItem);
        orderItem = new OrderItem(id, 1517875200, new Date(1517875200000L));
        orderItemRepository.insert(orderItem);

        order = new Order(1, 102, 1524327556, new Date(1524327556000L));
//        orderRepository.insertSelective(order);
        id = order.getOrderId();
        orderItem = new OrderItem(id, 1524327556, new Date(1524327556000L));
        orderItemRepository.insert(orderItem);
        orderItem = new OrderItem(id, 1524327556, new Date(1524327556000L));
        orderItemRepository.insert(orderItem);
    }

    @Test
    public void insertOrder() {
        Order order = new Order(2, 102, 1517875200, new Date(1517875200000L));
//        orderRepository.insertCustom(order);
    }

    @Test
    public void insertItem() {
        long id = 33L;
        OrderItem orderItem = new OrderItem(id, 1517875200, new Date(1517875200000L));
        orderItemRepository.insert(orderItem);
        orderItem = new OrderItem(id, 1517875200, new Date(1517875200000L));
        orderItemRepository.insert(orderItem);
    }

//    @Test
//    public void truncate() {
//        orderRepository.truncateTable();
//        orderItemRepository.truncateTable();
//        System.out.println("truncate success");
//    }
//
//    @Test
//    public void drop() {
//        orderItemRepository.dropTable();
//        orderRepository.dropTable();
//        System.out.println("drop success");
//    }

    @Test
    public void select() {
        OrderQuery query = new OrderQuery();
//        query.setCreateTime(1);
        query.setBuyer(1);
        query.setcStart(1505998800);
        query.setcEnd(1524327556);
        query.setuStart(new Date(1517875200000L));
        query.setuEnd(new Date(1524327556000L));
        System.out.println(orderRepository.selectAll(query));
//        orderRepository.selectByPrimaryKey(1L);
    }

}
