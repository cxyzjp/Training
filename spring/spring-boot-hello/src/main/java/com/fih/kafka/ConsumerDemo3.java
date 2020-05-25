package com.fih.kafka;

public class ConsumerDemo3 {
	
	public static void main(String[] args) {
		KafkaConsumer consumerThread = new KafkaConsumer(KafkaProperties.topic, "os7local03:2181");
		consumerThread.start();
	}
}
