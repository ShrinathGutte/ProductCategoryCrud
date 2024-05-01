package com.excelr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excelr.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
