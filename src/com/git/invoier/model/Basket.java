package com.git.invoier.model;

import java.util.ArrayList;
import java.util.List;

public class Basket {

	private List<BasketProduct> basketProducts = new ArrayList<>();

	public List<BasketProduct> getBasketProducts() {
		return basketProducts;
	}

	public void setBasketProducts(List<BasketProduct> basketProducts) {

		// immutable
		this.basketProducts = basketProducts;
	}

	
	

}
