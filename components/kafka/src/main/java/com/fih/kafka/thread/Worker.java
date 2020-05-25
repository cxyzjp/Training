package com.fih.kafka.thread;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class Worker implements Runnable {

	private ConsumerRecord<String, String> consumerRecord;

	public Worker(ConsumerRecord<String, String> record) {
		this.consumerRecord = record;
	}

	@Override
	public void run() {
		// 消息处理逻辑
//		System.out.println(Thread.currentThread().getName() + " consumed " + consumerRecord.partition()
//				+ "th message with offset: " + consumerRecord.offset());
		
		System.out.println(Thread.currentThread().getName() + ">>>> partition = " 
				+ consumerRecord.partition() + ", offset = " + consumerRecord.offset() + ", value = " 
				+ consumerRecord.value());
		
		if("goods".equals(consumerRecord.value())){
			try {
				Class<?> c = Class.forName("com.fih.kafka.thread.Goods");
				Object o = c.newInstance();
				Goods g = (Goods) o;
				g.info();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
