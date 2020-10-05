package com.git.invoier.model;

public class Product {

	private String name;
	private Double unitPrice;
	private boolean isImported;
	private ProductType type;

	public Product(String name, ProductType type, double unitPrice, boolean isImported) {
		this.type = type;
		this.name = name;
		this.unitPrice = unitPrice;
		this.isImported = isImported;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	public boolean getIsImported() {
		return isImported;
	}

	public void setIsImported(boolean isImported) {
		this.isImported = isImported;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

}
