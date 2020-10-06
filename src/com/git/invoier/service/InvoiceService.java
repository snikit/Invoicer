package com.git.invoier.service;

import com.git.invoier.model.Basket;
import com.git.invoier.model.BasketProduct;
import com.git.invoier.util.AppConstants;

public class InvoiceService {

	public void print(Basket basket) {

		double subtotal = 0d;
		double totalVAT = 0d;
		double totalAdditionalTax = 0d;

		/**
		 * printing hardcoded header
		 */
		System.out.println("NAME | QTY | UNIT_COST | COST");
		System.out.println("-----------------------------");

		for (BasketProduct basketProduct : basket.getBasketProducts()) {

			double totalPriceForBasketProduct = basketProduct.getTotalPrice();
			subtotal += totalPriceForBasketProduct;

			if (!AppConstants.TAX_EXEMPTED_TYPES.contains(basketProduct.getType())) {
				totalVAT += AppConstants.VAT_TAX * totalPriceForBasketProduct;
			}

			if (basketProduct.getIsImported()) {
				totalAdditionalTax += AppConstants.IMPORT_TAX * totalPriceForBasketProduct;
			}

			System.out.printf("%s | %s | %.2f | %.2f%n", basketProduct.getName(), basketProduct.getQuanity(),
					basketProduct.getUnitPrice(), totalPriceForBasketProduct);

		}
		/**
		 * printing final total footer
		 */
		System.out.printf("%nSubtotal : %.2f%nVAT : %.2f%nAdditional TAX : %.2f%nSubtotal : %.2f%n", subtotal, totalVAT,
				totalAdditionalTax, subtotal + totalVAT + totalAdditionalTax);

	}

}
