package com.fih.kafka.demo;

public class Main {
    public static void main(String[] args) {
    	String topic = "topic2";
    	
        Producer producerThread = new Producer(topic);
        producerThread.start();

        Consumer consumerThread = new Consumer(topic);
        consumerThread.start();

    }
}
