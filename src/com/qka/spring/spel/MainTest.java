package com.qka.spring.spel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {
	public static void main(String[] args) {

		ApplicationContext ctx1 = new ClassPathXmlApplicationContext("classpath:com/qka/spring/spel/spelContext.xml");
		Address a = (Address) ctx1.getBean("address");
		System.out.println("a = [" + a.toString() + "]");

		Car c = (Car) ctx1.getBean("car");
		System.out.println("c = [" + c + "]");

		Person p1 = (Person) ctx1.getBean("person1");
		System.out.println("p=[" + p1.toString() + "]");
	}
}
