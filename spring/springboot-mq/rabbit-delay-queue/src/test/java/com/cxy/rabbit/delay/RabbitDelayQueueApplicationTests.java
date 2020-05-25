package com.cxy.rabbit.delay;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitDelayQueueApplicationTests {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testDelayQueuePerMessageTTL() throws InterruptedException {
        ProcessReceiver.latch = new CountDownLatch(3);
        for (int i = 1; i <= 3; i++) {
            long expiration = (4 - i) * 1000;
            log.info("Send message " + i);
            rabbitTemplate.convertAndSend(QueueConfig.DELAY_QUEUE_PER_MESSAGE_TTL_NAME,
                    (Object) ("Message " + i + " From delay_queue_per_message_ttl with expiration " + expiration),
                    new ExpirationMessagePostProcessor(expiration));
        }
        ProcessReceiver.latch.await();
    }

    @Test
    public void testDelayQueuePerQueueTTL() throws InterruptedException {
        ProcessReceiver.latch = new CountDownLatch(3);
        for (int i = 1; i <= 3; i++) {
            rabbitTemplate.convertAndSend(QueueConfig.DELAY_QUEUE_PER_QUEUE_TTL_NAME,
                    "Message From delay_queue_per_queue_ttl with expiration " + QueueConfig.QUEUE_EXPIRATION);
        }
        ProcessReceiver.latch.await();
    }

    @Test
    public void testFailMessage() throws InterruptedException {
        ProcessReceiver.latch = new CountDownLatch(6);
        for (int i = 1; i <= 3; i++) {
            rabbitTemplate.convertAndSend(QueueConfig.DELAY_PROCESS_QUEUE_NAME, ProcessReceiver.FAIL_MESSAGE);
        }
        ProcessReceiver.latch.await();
    }

    @Test
    public void contextLoads() {
    }

}
