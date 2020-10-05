package com.git.invoier.model;

public class BasketProduct extends Product {

	private int Quanity;

	public BasketProduct(String name, ProductType type, double price, boolean isImported, int quanity) {
		super(name, type, price, isImported);
		Quanity = quanity;
	}

	public int getQuanity() {
		return Quanity;
	}

	public void setQuanity(int quanity) {
		Quanity = quanity;
	}

	public Double getTotalPrice() {
		return this.getUnitPrice() * this.getQuanity();
	}

}
