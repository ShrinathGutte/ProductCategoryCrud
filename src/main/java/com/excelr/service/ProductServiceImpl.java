package com.excelr.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.excelr.entity.Category;
import com.excelr.entity.Product;
import com.excelr.entity.ProductWithCategoryDTO;
import com.excelr.exception.IdNotFoundException;
import com.excelr.exception.InValidPriceException;
import com.excelr.repository.ProductRepository;
import com.excelr.utility.AppConstant;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Service
public class ProductServiceImpl implements ProductService {
	
	@PersistenceContext
    private EntityManager entityManager;

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Product addProduct(Product product) {
		
		if(product.getPrice()<=0)
		{
			throw new InValidPriceException(AppConstant.INAVLID_PRICE_EXCEPTION);
		}
		
	    Product product2=productRepository.save(product);
		return product2;
	}

	

//	@Override
//	public Product getProductById(int id) {
//		
//	   Optional<Product> optionalProduct =  productRepository.findById(id);
//	  Product product=null;
//	  
//	  if(optionalProduct.isPresent())
//	   {
//		   product= optionalProduct.get();
//		   product.getCategory().getCategoryName();
//	   }
//	  else 
//	  {
//		  throw new IdNotFoundException(AppConstant.ID_NOT_FOUND_MESSAGE);
//	  }
//		
//		return product;
//	}
	
	@Override
	public String deleteProduct(int id) {
		String msg="";
		   if(productRepository.existsById(id))
		   {
			   productRepository.deleteById(id);
			   msg="product deleted";
		   }
		   else
		   {
			   throw new IdNotFoundException(AppConstant.ID_NOT_FOUND_MESSAGE);
		   }
		
		return msg;
	}

	
	@Override
	public String updateProduct(int id, Product product) {
		String msg="";
		   if(productRepository.existsById(id))
		   {
			    Product product2 = productRepository.findById(id).get();
			    product2.setName(product.getName());
			    product2.setPrice(product.getPrice());
			    product2.setCategory(product.getCategory());
			    
			    productRepository.save(product2);
			    msg="product successfull updated";
			    
		   }
		   else
		   {
			   throw new IdNotFoundException(AppConstant.ID_NOT_FOUND_MESSAGE);
		   }
		return msg;
	}



	@Override
	public Page<Product> getAllProducts(Pageable pageable) {
		
		return productRepository.findAll(pageable);
	}



	@Override
	public ProductWithCategoryDTO getProductById(int id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
	    
	    if (optionalProduct.isPresent()) {
	        Product product = optionalProduct.get();
	        Category category = product.getCategory();
	        return new ProductWithCategoryDTO(product, category);
	    } else {
	        throw new IdNotFoundException(AppConstant.ID_NOT_FOUND_MESSAGE);
	    }
	}
	
	


	

}



	


