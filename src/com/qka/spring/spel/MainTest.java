package com.qka.spring.spel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:com/qka/spring/spel/spelContext.xml");
		Person p = (Person) ctx.getBean("person");
		System.out.println("p=[" + p.toString() + "]");
		ApplicationContext ctx1 = new ClassPathXmlApplicationContext("classpath:com/qka/spring/spel/spelContext.xml");
		Person p1 = (Person) ctx1.getBean("person1");
		System.out.println("p=[" + p1.toString() + "]");
	}
}
