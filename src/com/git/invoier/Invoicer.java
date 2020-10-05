package com.git.invoier;

import com.git.invoier.model.Basket;
import com.git.invoier.service.BasketService;
import com.git.invoier.service.InvoiceService;

public class Invoicer {

	public static void main(String[] args) {

		Basket basket = new BasketService().parse("c://basket.txt");
		new InvoiceService().print(basket);

	}
}
