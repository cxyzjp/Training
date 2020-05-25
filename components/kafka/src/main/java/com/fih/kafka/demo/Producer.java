package com.fih.kafka.demo;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Producer extends Thread {

	private final KafkaProducer<Integer, String> producer;
	private final String topic;

	public Producer(String topic) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "os7local00:9092,os7local01:9092,os7local02:9092");
		props.put("client.id", "DemoProducer");
		props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producer = new KafkaProducer<>(props);
		this.topic = topic;
	}

	public void run() {
		int messageNo = 1;
		while (true) {
			String messageStr = "Message_" + messageNo;
			try {
				producer.send(new ProducerRecord<>(topic, messageNo, messageStr)).get();
				System.out.println("+++++ Sent message: (" + messageNo + ", " + messageStr + ")");
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			++messageNo;
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
