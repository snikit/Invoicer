package com.git.invoier.model;

import java.util.ArrayList;
import java.util.List;

public class Basket {

	private List<BasketProduct> basketProducts = new ArrayList<>();

	public Basket(List<BasketProduct> basketProducts) {
		this.basketProducts = new ArrayList<BasketProduct>(basketProducts);
	}

	public List<BasketProduct> getBasketProducts() {
		return new ArrayList<BasketProduct>(basketProducts);
	}

}
