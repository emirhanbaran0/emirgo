package com.projemodel;

import java.util.Date;

public class Product {

	private int productId;
	private String productName;
	private double productPrice;
	private int stokNumber;
	private Date addDate;
	private Date upDate;
	private Category category;
	private Brand brand;
	
	
	
	
	public Product() {
		
	}
	public Product(int productId, String productName, double productPrice, int stokNumber, Date addDate,Date upDate, Category category,
			Brand brand) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.stokNumber = stokNumber;
		this.addDate = addDate;
		this.upDate=upDate;
		this.category = category;
		this.brand = brand;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public int getStokNumber() {
		return stokNumber;
	}
	public void setStokNumber(int stokNumber) {
		this.stokNumber = stokNumber;
	}
	public Date getDate() {
		return addDate;
	}
	public void setDate(Date date) {
		this.addDate = date;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Date getUpDate() {
		return upDate;
	}
	public void setUpDate(Date upDate) {
		this.upDate = upDate;
	}
	
	
	
}
