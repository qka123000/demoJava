package com.qka.spring.appctx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:com/qka/spring/appctx/applicationContext.xml");
		Person p = (Person)ctx.getBean("person");
		System.out.println("p=["+p.toString()+"]");
	}
}
