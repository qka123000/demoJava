package com.qka.spring.cycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/*
 * - ������ʵ��beanpostprocesser,��������bean
- ʵ�ֽӿڷ���,ͨ�����ﷵ��ԭ��beanֻ������bean��ֵ���
- �ӿڷ���һ����beforexxxһ����afterxxx,�ֱ���initǰ��ִ��;

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
