package com.excelr.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.excelr.entity.Product;
import com.excelr.entity.ProductWithCategoryDTO;

public interface ProductService {

	Product addProduct(Product product);
	Page<Product> getAllProducts(Pageable pageable);
	ProductWithCategoryDTO getProductById(int id);
	String deleteProduct(int id);
	String updateProduct(int id, Product product);
	

}
