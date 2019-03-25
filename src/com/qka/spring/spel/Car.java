package com.qka.spring.spel;

public class Car {
	private String brand;
	private double price;
	private static String speed = "1111";

	public static String getSpeed() {
		return speed;
	}

	public static void setSpeed(String speed) {
		Car.speed = speed;
	}

	public String getBrand() {
		return brand;
	}

	@Override
	public String toString() {
		return "Car [brand=" + brand + ", price=" + price + ", getBrand()=" + getBrand() + ", getPrice()=" + getPrice()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
