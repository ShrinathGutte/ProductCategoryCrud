package com.excelr.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.excelr.entity.Category;

public interface CategoryService {
	
	Category addCategory(Category category);
	Page<Category> getAllCategories(Pageable pageable);
	Category getCategoryById(int id);
	String deleteCategoryById(int id);
	String updateCategoryById(int id, Category category);

}
