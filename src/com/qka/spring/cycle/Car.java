package com.qka.spring.cycle;

public class Car {

	// Ʒ������
	private String brand;
	// ����carbean,�޲ι�����

	public Car() {
		System.out.println("constructer car ...");
	}

	// init����
	public void initCar() {
		System.out.println("init car ... ");
	}

	// get/set����
	public String getBrand() {
		System.out.println("get brand");
		return brand;
	}

	public void setBrand(String brand) {
		System.out.println("set brand");
		this.brand = brand;
	}

	// distory����
	private void distoryCar() {
		// TODO Auto-generated method stub
		System.out.println("distory car");

	}

}
