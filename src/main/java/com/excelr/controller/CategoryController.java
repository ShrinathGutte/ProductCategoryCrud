package com.excelr.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excelr.entity.Category;
import com.excelr.service.CategoryService;

@RestController
@RequestMapping("/api/")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("categories")
	public ResponseEntity<Page<Category>>getAllCategories(Pageable pageable)
	{
		return new ResponseEntity<Page<Category>>(categoryService.getAllCategories(pageable), HttpStatus.OK);
	}
	
	@PostMapping("categories")
	public ResponseEntity<Category> addCategory(@RequestBody Category category)
	{
		return new ResponseEntity<Category>(categoryService.addCategory(category), HttpStatus.OK);
	}
	
	@GetMapping("categories/{di}")
	public ResponseEntity<Category> getCategoryById(@PathVariable int di)
	{
		return new ResponseEntity<Category>(categoryService.getCategoryById(di), HttpStatus.OK);
	}
	
	@PutMapping("categories/{di}")
	public ResponseEntity<String> updateCategoryById(@PathVariable int di, @RequestBody Category category)
	{
		return new ResponseEntity<String>(categoryService.updateCategoryById(di, category), HttpStatus.OK);
	}
	
	@DeleteMapping("categories/{di}")
	public ResponseEntity<String> deleteCategoryById(@PathVariable int di)
	{
		return new ResponseEntity<String>(categoryService.deleteCategoryById(di), HttpStatus.OK);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public String  myExceptionResponse()
	{
		return "no id found to get";
	}

}
