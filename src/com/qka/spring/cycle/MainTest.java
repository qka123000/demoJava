package com.qka.spring.cycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {
	public static void main(String[] args) {

		ClassPathXmlApplicationContext ctx1 = new ClassPathXmlApplicationContext("classpath:com/qka/spring/cycle/cycleContext.xml");
		Car car = (Car) ctx1.getBean("car");
		
		//关闭时会调用bean的destroy方法;
		ctx1.close();

	}
}
