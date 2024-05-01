package com.excelr.entity;

public class ProductWithCategoryDTO {
	private Product product;
    private Category category;
    
	public ProductWithCategoryDTO(Product product, Category category) {
		super();
		this.product = product;
		this.category = category;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
    
    

}
