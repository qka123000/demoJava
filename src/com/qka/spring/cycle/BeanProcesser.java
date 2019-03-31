package com.qka.spring.cycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/*
 * - 创建类实现beanpostprocesser,处理所有bean
- 实现接口方法,通常用语返回原生bean只是用于bean赋值检查
- 接口方法一个是beforexxx一个是afterxxx,分别在init前后执行;

 * */
public class BeanProcesser implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object beanObject, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("postProcessAfterInitialization ... beanObject=["+beanObject+"] , beanName = ["+beanName+"]");
		return beanObject;
	}

	@Override
	public Object postProcessBeforeInitialization(Object beanObject, String beanName) throws BeansException {
		System.out.println("postProcessAfterInitialization ... beanObject=["+beanObject+"] , beanName = ["+beanName+"]");
		// TODO Auto-generated method stub
		return beanObject;
	}

}
