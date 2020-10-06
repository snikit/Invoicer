package com.git.invoier.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.git.invoier.exception.BasketException;
import com.git.invoier.exception.BasketIOException;
import com.git.invoier.exception.EmptyBasketInputException;
import com.git.invoier.exception.InvalidBasketInputException;
import com.git.invoier.model.Basket;
import com.git.invoier.model.BasketProduct;
import com.git.invoier.model.ProductType;
import com.git.invoier.util.AppConstants;

public class BasketService {

	public Basket parse(String basketFileName) throws BasketException {

		List<BasketProduct> basketProducts = null;

		try (Stream<String> stream = Files.lines(Paths.get(basketFileName))) {

			/**
			 * 1. parsing basket input file line by line 2. mapping each line string to
			 * basket product using mapToBasketProduct 3. collecting in a list of
			 * BasketProduct
			 */

			basketProducts = stream.map(basketString -> this.mapToBasketProduct(basketString))
					.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
			throw new BasketIOException();
		}

		if (basketProducts.isEmpty()) {
			throw new EmptyBasketInputException();
		}

		return new Basket(basketProducts);
	}

	/**
	 * 
	 * example : 1 potato chips packet @ 22.50 - first this method splits using @ ,
	 * we get 2 strings (a)1 potato chips packet & (b) 22.50 - then we split (a)
	 * string using first index of space " " into and we get (c) quantity & (d) name
	 */
	public BasketProduct mapToBasketProduct(String basketString) throws BasketException {

		String[] basketStringInitialTokens = basketString.split(AppConstants.SPLIT_TOKEN);

		/**
		 * in case the input is not in expected format for now this method expects only
		 * one @ in basket product string
		 */

		if (basketStringInitialTokens.length < AppConstants.INITAL_EXPECTED_TOKEN_LENGTH) {
			throw new InvalidBasketInputException();
		}

		double price = Double.parseDouble(basketStringInitialTokens[1]); // (b)
		int splitIndexForQuantityNameString = basketStringInitialTokens[0].indexOf(" ");

		int quantity = Integer.parseInt(basketStringInitialTokens[0].substring(0, splitIndexForQuantityNameString)); // (c)
		String name = basketStringInitialTokens[0].substring(splitIndexForQuantityNameString); // (d)

		boolean isImported = getIsImported(name);
		ProductType type = getType(name);

		return new BasketProduct(name, type, price, isImported, quantity);

	}

	/**
	 * logic is simply searching for "imported" text in name string
	 * 
	 * @param productName
	 * @return
	 */
	private boolean getIsImported(String productName) {
		return productName.contains(AppConstants.IMPORTED_TOKEN);
	}

	/**
	 * there was no real logic to get product type, so for now maintaining a map of
	 * product types and names.
	 * 
	 * @param productName
	 * @return
	 */
	private ProductType getType(String productName) {

		for (Entry<ProductType, List<String>> productTypeEntry : AppConstants.PRODUCT_TYPES_MAPPER.entrySet())
			for (String expectedProductName : productTypeEntry.getValue())
				if (productName.contains(expectedProductName))
					return productTypeEntry.getKey();

		return ProductType.ANONYMOUS;

	}

}
