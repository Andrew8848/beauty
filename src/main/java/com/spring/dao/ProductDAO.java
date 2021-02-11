package com.spring.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.spring.model.Product;
import com.spring.model.ProductForm;

@Repository
public class ProductDAO {

	private static final Map<Integer, Product> productMap = new HashMap<Integer, Product>();

	static {
		initial();
	}

	public static void initial() {
//		Product p = new Product(new String[]{"2342345tresg4324523et2weater"}, 140, new String[]{"Nutrition", "Moisturizing"}, "Coconut body oil, brand", "Elit-Lab", new String[] {"150ml"}, "18+", "Coconut oil is a unique natural product", "Body care", "Ukraine", "Ukraine", "nutrition, hydration", "universal", "for women", "professional");
//		productMap.put(1, p);
//		Product p2 = new Product(new String[]{"23845yug23784t578234578234yt5uty2"}, 140, new String[]{"Nutrition", "Moisturizing"}, "Coconut body oil, brand", "Elit-Lab", new String[] {"150ml"}, "18+", "Coconut oil is a unique natural product", "Body care", "Ukraine", "Ukraine", "nutrition, hydration", "universal", "for women", "professional");
//		productMap.put(2, p2);

	}

	public Integer getMaxProductId(){
		Set<Integer> keys = productMap.keySet();
		Integer max = 0;
		for (Integer key : keys) {
			if (key > max) {
				max = key;
			}
		}
		return max;
	}

	public Product getProduct(Integer productId) {
		return productMap.get(productId);
	}

	public Product add(ProductForm productForm) {
		Integer productId = getMaxProductId();
		//productForm.setId(productId);
		Product newProduct = new Product(productForm);

		productMap.put(productId, newProduct);

		return newProduct;
	}

	public List<Product> getAllProducts() {
		Collection<Product> collection = productMap.values();
		List<Product> list = new ArrayList<Product>();
		list.addAll(collection);
		return list;
	}

}
