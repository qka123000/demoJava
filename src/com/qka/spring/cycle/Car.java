package com.qka.spring.cycle;

public class Car {

	// 品牌属性
	private String brand;
	// 创建carbean,无参构造器

	public Car() {
		System.out.println("constructer car ...");
	}

	// init方法
	public void initCar() {
		System.out.println("init car ... ");
	}

	// get/set方法
	public String getBrand() {
		System.out.println("get brand");
		return brand;
	}

	public void setBrand(String brand) {
		System.out.println("set brand");
		this.brand = brand;
	}

	// distory方法
	private void distoryCar() {
		// TODO Auto-generated method stub
		System.out.println("distory car");

	}

}
