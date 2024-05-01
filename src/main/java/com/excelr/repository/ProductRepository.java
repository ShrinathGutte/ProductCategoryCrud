package com.excelr.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.excelr.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	
	
}
