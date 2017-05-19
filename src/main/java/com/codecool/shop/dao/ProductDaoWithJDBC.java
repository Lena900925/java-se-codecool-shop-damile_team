package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.io.IOException;
import java.util.List;

/**
 * <h1>ProductDaoWithJDBC interface</h1> uses JDBC
 *
 * @author arinyu
 */
public interface ProductDaoWithJDBC {
    List<Product> listAllProducts() throws IOException;

    List<Product> getProductBy(Supplier supplier) throws IOException;

    List<Product> getProductBy(ProductCategory productCategory) throws IOException;

    void add(Product product);
}
