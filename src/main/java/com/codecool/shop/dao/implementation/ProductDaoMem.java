package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1>ProductDaoMem class</h1> for storing products.<p>Implements the ProductDao interface.</p>
 *
 * @author arinyu
 */
public class ProductDaoMem implements ProductDao {

    private List<Product> DATA = new ArrayList<>();
    private static ProductDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoMem() {
    }

    /**
     * If there is no instance, it creates one. If it exist, it returns it.
     *
     * @return instance
     */
    public static ProductDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductDaoMem();
        }
        return instance;
    }

    /**
     * Adds a product of Product
     *
     * @param product Product to add
     */
    @Override
    public void add(Product product) {
        product.setId(DATA.size() + 1);
        DATA.add(product);
    }

    /**
     * Finds the product
     *
     * @param id ID of the product
     * @return data with the given id
     */
    @Override
    public Product find(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID can't be under 0");
        }
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    /**
     * Removes product with the given id
     *
     * @param id ID of the removable product
     */
    @Override
    public void remove(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID can't be under 0");
        }
        DATA.remove(find(id));
    }

    /**
     * Lists all products
     *
     * @return List of products
     */
    @Override
    public List<Product> getAll() {
        return DATA;
    }

    /**
     * Lists all products filtered by the given supplier
     *
     * @return List of filtered products
     */
    @Override
    public List<Product> getBy(Supplier supplier) {
        return DATA.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    /**
     * Lists all products filtered by the given product category
     *
     * @return List of filtered products
     */
    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return DATA.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }
}
