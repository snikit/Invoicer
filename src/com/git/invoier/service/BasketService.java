package com.git.invoier.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.git.invoier.model.Basket;
import com.git.invoier.model.BasketProduct;
import com.git.invoier.model.ProductType;

public class BasketService {

	private static final int INITAL_EXPECTED_TOKEN_LENGTH = 2;
	private static final String SPLIT_TOKEN = "@";
	private static final String IMPORTED_TOKEN = "imported";

	private final static Map<ProductType, List<String>> PRODUCT_TYPES_MAPPER = new HashMap<ProductType, List<String>>() {
		private static final long serialVersionUID = 1L;

		{
			put(ProductType.MEDICINES, Arrays.asList("crocin", "disprin"));
			put(ProductType.FOOD, Arrays.asList("bread", "meat"));
		}
	};

	public Basket parse(String basketFileName) {

		List<BasketProduct> basketProducts = null;
		try (Stream<String> stream = Files.lines(Paths.get(basketFileName))) {

			basketProducts = stream.map(basketString -> this.mapToBasketProduct(basketString))
					.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		Basket basket = new Basket();
		basket.setBasketProducts(basketProducts);
		return basket;
	}

	public BasketProduct mapToBasketProduct(String basketString) {

		String[] basketStringInitialTokens = basketString.split(SPLIT_TOKEN);

		if (basketStringInitialTokens.length < INITAL_EXPECTED_TOKEN_LENGTH) {
			throw new RuntimeException("length issue");
		}

		double price = Double.parseDouble(basketStringInitialTokens[1]);
		int splitIndexForQuantityNameString = basketStringInitialTokens[0].indexOf(" ");

		int quantity = Integer.parseInt(basketStringInitialTokens[0].substring(0, splitIndexForQuantityNameString));
		String name = basketStringInitialTokens[0].substring(splitIndexForQuantityNameString);

		boolean isImported = getIsImported(name);
		ProductType type = getType(name);

		return new BasketProduct(name, type, price, isImported, quantity);

	}

	private boolean getIsImported(String productName) {
		return productName.contains(IMPORTED_TOKEN);
	}

	private ProductType getType(String productName) {

		for (Entry<ProductType, List<String>> productTypeEntry : PRODUCT_TYPES_MAPPER.entrySet())
			for (String expectedProductName : productTypeEntry.getValue())
				if (productName.contains(expectedProductName))
					return productTypeEntry.getKey();

		return ProductType.ANONYMOUS;

	}

}
