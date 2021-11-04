package com.unibuc.ex2curs4.filter;


import com.unibuc.ex2curs4.model.*;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoggingFilter extends HttpFilter {
    private ProductLoggingDetails productLoggingDetails;
    private ShoppingCart shoppingCart;

    public LoggingFilter(ProductLoggingDetails productLoggingDetails, ShoppingCart shoppingCart) {
        this.productLoggingDetails = productLoggingDetails;
        this.shoppingCart = shoppingCart;
    }

    @Override
    public void doFilter(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1. logic that executes before the request reaches the controller

        //2.
        filterChain.doFilter(servletRequest, servletResponse);

        //3. logic that executes after the code from the controller finishes its execution
        System.out.println("=== Logging filter: Product logging details instance details - " + productLoggingDetails);
        if(productLoggingDetails.getProduct() != null) {
            System.out.println("=== Logging filter: Product " + productLoggingDetails.getProduct() +
                    " was created successfully at " + productLoggingDetails.getCreationDateTime());
            System.out.println("=== Logging filter: Shopping cart instance details - " + shoppingCart);
            System.out.println("=== Logging filter: Shopping cart contains " + shoppingCart.getProducts());
        }
    }
}
