package com.qka.spring.factoryBean;

import java.util.HashMap;
import java.util.Map;

public class StaticFactory {

	// - Map 类型属性
	private static Map<String, Object> cars = null;

	static {
		cars = new HashMap<>();
		cars.put("audi", new Car("audi", 333333));
		cars.put("ford", new Car("audi", 444444));
	}

	// - 获取car实例
	private static Map<String, Object> getCar() {
		// TODO Auto-generated method stub
		return cars;

	}

}
