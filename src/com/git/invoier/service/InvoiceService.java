package com.git.invoier.service;

import java.util.Arrays;
import java.util.List;

import com.git.invoier.model.Basket;
import com.git.invoier.model.BasketProduct;
import com.git.invoier.model.ProductType;

public class InvoiceService {

	private final static List<ProductType> TAX_EXEMPTED_TYPES = Arrays.asList(ProductType.FOOD, ProductType.TOYS);
	private final static double VAT_TAX = 0.125;
	private final static double IMPORT_TAX = 0.024;

	public void print(Basket basket) {

		double subtotal = 0d;
		double totalVAT = 0d;
		double totalAdditionalTax = 0d;

		System.out.println("NAME | QTY | UNIT_COST | COST");
		System.out.println("-----------------------------");

		for (BasketProduct basketProduct : basket.getBasketProducts()) {

			double totalPriceForBasketProduct = basketProduct.getTotalPrice();
			subtotal += totalPriceForBasketProduct;

			if (!TAX_EXEMPTED_TYPES.contains(basketProduct.getType())) {
				totalVAT += VAT_TAX * totalPriceForBasketProduct;
			}

			if (basketProduct.getIsImported()) {
				totalAdditionalTax += IMPORT_TAX * totalPriceForBasketProduct;
			}

			System.out.printf("%s | %s | %.2f | %.2f%n", basketProduct.getName(), basketProduct.getQuanity(),
					basketProduct.getUnitPrice(), totalPriceForBasketProduct);

		}

		System.out.printf("%nSubtotal : %.2f%nVAT : %.2f%nAdditional TAX : %.2f%nSubtotal : %.2f%n", subtotal, totalVAT,
				totalAdditionalTax, subtotal + totalVAT + totalAdditionalTax);

	}

}
