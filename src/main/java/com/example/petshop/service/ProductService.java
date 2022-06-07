package com.example.petshop.service;

import com.example.petshop.dto.ProductCategory;
import com.example.petshop.entity.Category;
import com.example.petshop.entity.Product;
import com.example.petshop.repository.CategoryRepository;
import com.example.petshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void createProduct(ProductCategory productCategory) {
        Category category = categoryRepository.findCategoryByName(productCategory.getCategory());
        Product product = new Product(
                category,
                productCategory.getName(),
                productCategory.getDescription(),
                productCategory.getPhotoUrl(),
                productCategory.getPrice()
        );
        productRepository.save(product);
    }

    public void createCategory(Category productType) {
        categoryRepository.save(productType);
    }
}
