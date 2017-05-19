package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.util.ArrayList;
import java.util.List;
/**
 * <h1>ProductCategoryDaoMem class</h1> for storing categories of products.<p>Implements the ProductCategoryDao interface.</p>
 *
 * @author arinyu
 *
 */
public class ProductCategoryDaoMem implements ProductCategoryDao {

    private List<ProductCategory> DATA = new ArrayList<>();
    private static ProductCategoryDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductCategoryDaoMem() {
    }
    /**
     * If there is no instance, it creates one. If it exist, it returns it.
     * @return instance
     */
    public static ProductCategoryDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoMem();
        }
        return instance;
    }

    /**
     * Adds category of ProductCategory
     * @param category Category of the Product
     */
    @Override
    public void add(ProductCategory category) {
        category.setId(DATA.size() + 1);
        DATA.add(category);
    }
    /**
     * Finds the product category
     * @param id ID of the product category
     * @return data with the given id
     */
    @Override
    public ProductCategory find(int id) {
        if(id<0)
        {
            throw new IllegalArgumentException("id can't be under 0");
        }
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }
    /**
     * Finds the product category
     * @param name Name of the product category
     * @return data with the given name
     */
    @Override
    public ProductCategory find(String name) {
        ProductCategory temp=DATA.stream().filter(t -> t.getName().toLowerCase().contentEquals(name)).findFirst().orElse(null);
        return temp;
    }
    /**
     * Removes product with the given id
     * @param id ID of the removable product category
     */
    @Override
    public void remove(int id) {
        if(id<0)
        {
            throw new IllegalArgumentException("id can't be under 0");
        }
        DATA.remove(find(id));
    }

    /**
     * Lists all product categories
     *
     * @return List of product categories
     */
    @Override
    public List<ProductCategory> getAll() {
        return DATA;
    }
}
