package com.fih.kafka.thread;

public class MainConsumer {

	public static void main(String[] args) {
		
		String brokerList = "os7local00:9092,os7local01:9092,os7local02:9092";
		String groupId = "DemoConsumer";
		String topic = "topic2";
		int workerNum = 3;

		ConsumerHandler consumers = new ConsumerHandler(brokerList, groupId, topic);
		consumers.execute(workerNum);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException ignored) {
		}
		consumers.shutdown();
	}
	
}
