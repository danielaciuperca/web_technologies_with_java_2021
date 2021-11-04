package com.unibuc.ex2curs4.service;

import com.unibuc.ex2curs4.model.Product;
import com.unibuc.ex2curs4.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void create(Product product) {
        productRepository.create(product);
    }

    public List<Product> getAllProducts() {
        return  productRepository.getAllProducts();
    }
}
