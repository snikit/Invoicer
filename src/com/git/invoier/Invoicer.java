package com.git.invoier;

import com.git.invoier.exception.BasketException;
import com.git.invoier.service.BasketService;
import com.git.invoier.service.InvoiceService;

public class Invoicer {

	public static void main(String[] args) {

		try {
			new InvoiceService().print(new BasketService().parse("c://basket.txt"));
		} catch (BasketException e) {
			e.printStackTrace();

		}

	}
}
