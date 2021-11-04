package com.unibuc.ex2curs4.repository;

import com.unibuc.ex2curs4.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> products = new ArrayList<>();

    public void create(Product product) {
        products.add(product);
        System.out.println("Product " + product + " was saved in the database.");
    }

    public List<Product> getAllProducts() {
        return  products;
    }
}
