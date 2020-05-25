package com.fih.kafka;

public class ConsumerDemo2 {
	
	public static void main(String[] args) {
		KafkaConsumer consumerThread = new KafkaConsumer(KafkaProperties.topic, "os7local02:2181");
		consumerThread.start();
	}
}
