package com.codecool.shop.dao;
import com.codecool.shop.model.ProductCategory;

import java.io.IOException;
import java.util.List;

/**
 * <h1>ProductCategoryDaoWithJDBC interface</h1> uses JDBC
 *
 * @author arinyu
 */
public interface ProductCategoryDaoWithJDBC {

    List<ProductCategory> getAllCategories() throws IOException;
    ProductCategory findCategory(int id);
    ProductCategory findCategory(String name);
    void add(ProductCategory prodCat);
}
