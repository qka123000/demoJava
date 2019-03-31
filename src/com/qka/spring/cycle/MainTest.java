package com.qka.spring.cycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {
	public static void main(String[] args) {

		ClassPathXmlApplicationContext ctx1 = new ClassPathXmlApplicationContext("classpath:com/qka/spring/cycle/cycleContext.xml");
		Car car = (Car) ctx1.getBean("car");
		
		//�ر�ʱ�����bean��destroy����;
		ctx1.close();

	}
}
