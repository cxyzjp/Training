package com.fih;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.fih.utils.MyProps;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyPropsTest {

	@Autowired
	private MyProps myProps;

	@Test
	public void propsTest() {
		System.out.println("simpleProp: " + myProps.getSimpleProp());
		System.out.println("arrayProps: " + JSON.toJSONString(myProps.getArrayProps()));
		System.out.println("listProp1: " + JSON.toJSONString(myProps.getListProp1()));
		System.out.println("listProp2: " + JSON.toJSONString(myProps.getListProp2()));
		System.out.println("mapProps: " + JSON.toJSONString(myProps.getMapProps()));
		
		List<Map<String, String>> shiroChain = myProps.getShiroChain();
		for (Map<String, String> map : shiroChain) {
			System.out.println("key: " + map.get("key") + "  value: " + map.get("value"));
		}
	}

}
