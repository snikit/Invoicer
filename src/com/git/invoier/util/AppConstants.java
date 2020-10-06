package com.git.invoier.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.git.invoier.model.ProductType;

public class AppConstants {

	public final static List<ProductType> TAX_EXEMPTED_TYPES = Arrays.asList(ProductType.FOOD, ProductType.TOYS);
	public final static double VAT_TAX = 0.125;
	public final static double IMPORT_TAX = 0.024;
	public static final int INITAL_EXPECTED_TOKEN_LENGTH = 2;
	public static final String SPLIT_TOKEN = "@";
	public static final String IMPORTED_TOKEN = "imported";

	public final static Map<ProductType, List<String>> PRODUCT_TYPES_MAPPER = new HashMap<ProductType, List<String>>() {
		private static final long serialVersionUID = 1L;

		{
			put(ProductType.MEDICINES, Arrays.asList("crocin", "disprin"));
			put(ProductType.FOOD, Arrays.asList("bread", "meat"));
		}
	};

}
