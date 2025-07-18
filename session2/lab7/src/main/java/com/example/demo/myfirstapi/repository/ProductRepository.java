package com.example.demo.myfirstapi.repository;

import com.example.demo.myfirstapi.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {

    private final List<Product> products = Collections.synchronizedList(new ArrayList<>());
    private final AtomicLong nextId = new AtomicLong(1);

    public ProductRepository() {
        products.add(new Product(nextId.getAndIncrement(), "Laptop", 1200.00));
        products.add(new Product(nextId.getAndIncrement(), "Mouse", 25.50));
        products.add(new Product(nextId.getAndIncrement(), "Keyboard", 75.00));
        System.out.println("ProductRepository initialized with " + products.size() + " products.");
    }

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public Optional<Product> findById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(nextId.getAndIncrement());
            products.add(product);
            System.out.println("Product saved (new): " + product);
            return product;
        } else {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId().equals(product.getId())) {
                    products.set(i, product);
                    System.out.println("Product updated: " + product);
                    return product;
                }
            }

            System.out.println("Product with ID " + product.getId() + " not found for update.");
            return product;
        }
    }
}