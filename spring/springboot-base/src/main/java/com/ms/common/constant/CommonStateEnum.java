package com.ms.common.constant;

public enum CommonStateEnum {

	YES(1),		// 是
	NO(0)		// 否
	;
	
	private int state;
	
	private CommonStateEnum(int state){
		this.state = state;
	}
	
	public Byte getState(){
		return (byte)this.state;
	}
}
