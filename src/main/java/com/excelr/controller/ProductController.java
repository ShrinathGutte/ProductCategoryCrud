package com.excelr.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excelr.entity.Product;
import com.excelr.entity.ProductWithCategoryDTO;
import com.excelr.service.ProductService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@PostMapping("/products")
	public ResponseEntity<Product> addProduct(@RequestBody Product product)
	{
		return new ResponseEntity<Product>(productService.addProduct(product), HttpStatus.OK);
		
	}
	
	@GetMapping("/products")
	public ResponseEntity<Page<Product>> getAllProducts(Pageable pageable)
	{
		return new ResponseEntity<Page<Product>>(productService.getAllProducts(pageable), HttpStatus.OK);
	}
	
	
	@GetMapping("/products/{di}")
	public ResponseEntity<ProductWithCategoryDTO> getProductById(@PathVariable int di)
	{
		return new ResponseEntity<ProductWithCategoryDTO>(productService.getProductById(di), HttpStatus.OK);
	}
	
	@DeleteMapping("/products/{di}")
	public ResponseEntity<String> deleteProduct(@PathVariable int di)
	{
		return new ResponseEntity<String>(productService.deleteProduct(di), HttpStatus.OK);
	}
	 
	@PutMapping("/products/{di}")
	public ResponseEntity<String> updateProduct(@PathVariable int di, @RequestBody Product product)
	{
		return new ResponseEntity<String>(productService.updateProduct(di, product), HttpStatus.OK);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public String  myExceptionResponse()
	{
		return "no id found to get";
	}

	
	
}