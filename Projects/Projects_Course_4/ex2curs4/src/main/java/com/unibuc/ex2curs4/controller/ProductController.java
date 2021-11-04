package com.unibuc.ex2curs4.controller;

import com.unibuc.ex2curs4.model.*;
import com.unibuc.ex2curs4.service.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.*;

@Controller
public class ProductController {

    private ProductService productService;
    private ProductLoggingDetails productLoggingDetails;
    private ShoppingCart shoppingCart;


    public ProductController(ProductService productService, ProductLoggingDetails productLoggingDetails, ShoppingCart shoppingCart) {
        this.productService = productService;
        this.productLoggingDetails = productLoggingDetails;
        this.shoppingCart = shoppingCart;
    }

    @PostMapping(value = "/product")
    public String create(Product product, Model model) {
        productService.create(product);

        productLoggingDetails.setProduct(product);
        productLoggingDetails.setCreationDateTime(LocalDateTime.now());

        addProductsToModel(model);

        shoppingCart.getProducts().add(product);

        return "addProduct";
    }

    private void addProductsToModel(Model model) {
        model.addAttribute("product", new Product());
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
    }

    @GetMapping("/product")
    public String get(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

}
