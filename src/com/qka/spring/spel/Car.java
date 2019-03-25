package com.qka.spring.spel;

public class Car {
	private String brand;
	private double price;

	private double circle;
	private String city;


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getCircle() {
		return circle;
	}

	public void setCircle(double circle) {
		this.circle = circle;
	}

	public String getBrand() {
		return brand;
	}

	@Override
	public String toString() {
		return "Car [brand=" + brand + ", price=" + price + ", circle=" + circle + ", city=" + city + "]";
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
