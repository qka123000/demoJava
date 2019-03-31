package com.qka.spring.factoryBean;

import java.util.HashMap;
import java.util.Map;

public class StaticFactory {

	// - Map ��������
	private static Map<String, Object> cars = null;

	static {
		cars = new HashMap<>();
		cars.put("audi", new Car("audi", 333333));
		cars.put("ford", new Car("audi", 444444));
	}

	// - ��ȡcarʵ��
	private static Map<String, Object> getCar() {
		// TODO Auto-generated method stub
		return cars;

	}

}
