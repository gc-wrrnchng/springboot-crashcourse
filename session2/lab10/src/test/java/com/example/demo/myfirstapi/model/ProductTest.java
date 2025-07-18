package com.example.demo.myfirstapi.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    Product product;

    @BeforeEach
    public void setUp() {
        product = new Product(1L, "Test Product", 99.99);
    }

    @AfterEach
    public void tearDown() {
        product = null;
    }

    @Test
    public void testGetId() {
        assertEquals(1L, product.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("Test Product", product.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(99.99, product.getPrice());
    }

    @Test
    public void testSetId() {
        product.setId(2L);
        assertEquals(2L, product.getId());
    }

    @Test
    public void testSetName() {
        product.setName("Updated Product");
        assertEquals("Updated Product", product.getName());
    }

    @Test
    public void testSetPrice() {
        product.setPrice(49.99);
        assertEquals(49.99, product.getPrice());
    }

    @Test
    public void testToString() {
        String expected = "Product{id=1, name='Test Product', price=99.99}";
        assertEquals(expected, product.toString());
    }

    @Test
    public void testEquals() {
        Product anotherProduct = new Product(1L, "Test Product", 99.99);
        assertEquals(product, anotherProduct);

        anotherProduct.setId(2L);
        assertNotEquals(product, anotherProduct);

        anotherProduct = null;
        assertNotEquals(anotherProduct, product);
    }

    @Test
    public void testHashCode() {
        Product anotherProduct = new Product(1L, "Test Product", 99.99);
        assertEquals(product.hashCode(), anotherProduct.hashCode());

        anotherProduct.setId(2L);
        assertNotEquals(product.hashCode(), anotherProduct.hashCode());
    }
}
