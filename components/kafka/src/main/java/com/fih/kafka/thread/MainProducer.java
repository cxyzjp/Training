package com.fih.kafka.thread;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class MainProducer {

	public static void main(String[] args) {
		String topic = "topic2";
		KafkaProducer<Integer, String> producer;
		
		Properties props = new Properties();
		props.put("bootstrap.servers", "os7local00:9092,os7local01:9092,os7local02:9092");
		props.put("client.id", "DemoProducer");
		props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producer = new KafkaProducer<>(props);
		
		for (int i = 0; i < 100; i++) {
			String messageStr = "Message_" + i;
			try {
				producer.send(new ProducerRecord<>(topic, 100+i, messageStr)).get();
				System.out.println("+++++ Sent message: (" + i + ", " + messageStr + ")");
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		try {
			producer.send(new ProducerRecord<>(topic, 999, "goods")).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		producer.close();
	}

}
