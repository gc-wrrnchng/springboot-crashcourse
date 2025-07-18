package com.example.demo.myfirstapi.repository;

import com.example.demo.myfirstapi.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
    }

    @AfterEach
    void tearDown() {
        productRepository = null;
    }

    @Test
    void findAll() {
        List products = productRepository.findAll();
        assertNotNull(products);
        assertFalse(products.isEmpty());
        assertEquals(3, products.size());
        for(Object product : products) {
            assertInstanceOf(Product.class, product);
            Product p = (Product) product;
            assertNotNull(p.getId());
            assertNotNull(p.getName());
            System.out.println("Product: " + p.getName() + ", Price: " + p.getPrice());
            assertTrue(p.getPrice() > 0, "Product price should be greater than zero");
        }
    }

    @Test
    void findById() {
        var product = productRepository.findById(1L);
        assertEquals("Laptop", product.get().getName());
        assertEquals(1200.00, product.get().getPrice());
        assertEquals(1L, product.get().getId());
        System.out.println("Found product: " + product.get().getName());

        var nonExistentProduct = productRepository.findById(999L);
        assertFalse(nonExistentProduct.isPresent());
    }

    @Test
    void save() {
        var newProduct = new Product(null, "Monitor", 300.00);
        var savedProduct = productRepository.save(newProduct);
        assertNotNull(savedProduct.getId());
        assertEquals("Monitor", savedProduct.getName());
        assertEquals(300.00, savedProduct.getPrice());
        assertTrue(productRepository.findAll().contains(savedProduct));

        // Test updating an existing product
        savedProduct.setPrice(320.00);
        var updatedProduct = productRepository.save(savedProduct);
        assertEquals(320.00, updatedProduct.getPrice());
        assertTrue(productRepository.findAll().contains(updatedProduct));

        // Test saving a product with an ID that does not exist
        var nonExistentProduct = new Product(999L, "NonExistent", 100.00);
        var result = productRepository.save(nonExistentProduct);
        assertEquals(nonExistentProduct, result);
        assertFalse(productRepository.findAll().contains(nonExistentProduct));
    }
}