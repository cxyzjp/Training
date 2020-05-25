package com.fih.kafka.thread.pool;

public class WorkerThread implements Runnable {

	private String command;
	 
    public WorkerThread(String s){
        this.command=s;
    }
 
    @Override
    public void run() {
//    	if("3".equals(command)){
//    		System.out.println(1/0);
//    	}
        System.out.println(Thread.currentThread().getName()+" Start. Command = "+command);
        processCommand();
        System.out.println(Thread.currentThread().getName()+" End.");
    }
 
    private void processCommand() {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

	public String getCommand() {
		return command;
	}

}
