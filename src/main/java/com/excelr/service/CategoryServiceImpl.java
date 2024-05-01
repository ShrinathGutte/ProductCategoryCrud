package com.excelr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.excelr.entity.Category;
import com.excelr.entity.Product;
import com.excelr.exception.IdNotFoundException;
import com.excelr.repository.CategoryRepository;
import com.excelr.utility.AppConstant;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Category addCategory(Category category) {

        Category categories = categoryRepository.save(category);
		return categories;
	}

	

	@Override
	public Category getCategoryById(int id) {
		Optional<Category> optionalCategory =  categoryRepository.findById(id);
		  Category category=null;
		  
		  if(optionalCategory.isPresent())
		   {
			   category= optionalCategory.get();
		   }
		  else 
		  {
			  throw new IdNotFoundException(AppConstant.ID_NOT_FOUND_MESSAGE);
		  }
		return category;
	}

	@Override
	public String deleteCategoryById(int id) {
		String msg="";
		   if(categoryRepository.existsById(id))
		   {
			   categoryRepository.deleteById(id);
			   msg="category deleted";
		   }
		   else
		   {
			   throw new IdNotFoundException(AppConstant.ID_NOT_FOUND_MESSAGE);
		   }
		
		return msg;
	}

	@Override
	public String updateCategoryById(int id, Category category) {
		String msg="";
		if (categoryRepository.existsById(id)) {
	        
	        Category managedCategory = categoryRepository.findById(id)
	                                                      .orElseThrow(() -> new IdNotFoundException(AppConstant.ID_NOT_FOUND_MESSAGE));

	        
	        managedCategory.setCategoryName(category.getCategoryName());

	        
	        for (Product product : category.getProducts()) {
	            Product existingProduct = managedCategory.getProducts()
	                                                     .stream()
	                                                     .filter(p -> p.getId() == product.getId())
	                                                     .findFirst()
	                                                     .orElseThrow(() -> new IdNotFoundException("Product with ID " + product.getId() + " not found"));
	            existingProduct.setName(product.getName());
	            existingProduct.setPrice(product.getPrice());
	        }

	       
	        categoryRepository.save(managedCategory);

	        msg = "Category successfully updated";
	    } else {
	        throw new IdNotFoundException(AppConstant.ID_NOT_FOUND_MESSAGE);
	    }
	    return msg;
	}



	@Override
	public Page<Category> getAllCategories(Pageable pageable) {
		
			return categoryRepository.findAll(pageable);
		
	}

}
