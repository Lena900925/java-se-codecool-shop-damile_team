package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDaoWithJDBC;
import com.codecool.shop.dao.ProductDaoWithJDBC;
import com.codecool.shop.dao.SupplierDaoWithJDBC;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMemWithJDBC;
import com.codecool.shop.dao.implementation.ProductDaoMemWithJDBC;
import com.codecool.shop.dao.implementation.SupplierDaoMemWithJDBC;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Controller class</h1> for managing Product, Supplier and ProductCategory objects.
 *
 * @author arinyu
 */
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private static ProductCategory filteredCategory;
    private static Supplier filteredSupplier;

    /**
     * Renders all products
     *
     * @param req request
     * @param res response
     * @throws IOException If no connection
     * @return an ModelAndView object to render a HashMap with the data in html.
     */
    public static ModelAndView renderProducts(Request req, Response res) throws IOException {
        ProductDaoWithJDBC productDaoWithJDBC = new ProductDaoMemWithJDBC();
        SupplierDaoWithJDBC supplierDaoWithJDBC = new SupplierDaoMemWithJDBC();
        ProductCategoryDaoWithJDBC productCategoryDaoWithJDBC = new ProductCategoryDaoMemWithJDBC();

        Map params = new HashMap<>();
        params.put("category", productCategoryDaoWithJDBC.getAllCategories());
        params.put("supplier", supplierDaoWithJDBC.getAllSupplier());
        params.put("products", productDaoWithJDBC.listAllProducts());
        if (params == null) {
            logger.warn("Products couldn't be loaded! Maybe your database is empty?");
        }
        logger.info("Products have been added successfully!");
        return new ModelAndView(params, "product/index");
    }

    /**
     * Renders the products filtered by the categories chosen by the user.
     *
     * @param req request
     * @param res response
     * @throws IOException If no connection
     * @return an ModelAndView object to render a HashMap with the data in html.
     */
    public static ModelAndView renderForCategory(Request req, Response res) throws IOException {
        ProductDaoWithJDBC productDaoWithJDBC = new ProductDaoMemWithJDBC();
        SupplierDaoWithJDBC supplierDaoWithJDBC = new SupplierDaoMemWithJDBC();
        ProductCategoryDaoWithJDBC productCategoryDaoWithJDBC = new ProductCategoryDaoMemWithJDBC();


        String selectedCategory = req.params(":categoryName");

        for (ProductCategory cat : productCategoryDaoWithJDBC.getAllCategories()) {
            if (selectedCategory.equals(cat.getName())) {
                filteredCategory = cat;
            }
        }
        Map params = new HashMap<>();
        params.put("category", productCategoryDaoWithJDBC.getAllCategories());
        params.put("products", productDaoWithJDBC.getProductBy(filteredCategory));
        params.put("supplier", supplierDaoWithJDBC.getAllSupplier());
        logger.info("User has chosen Product Category: {}", filteredCategory.getName());
        return new ModelAndView(params, "product/index");
    }

    /**
     * Renders the products filtered by the suppliers chosen by the user.
     *
     * @param req request
     * @param res response
     * @throws IOException If no connection
     * @return an ModelAndView object to render a HashMap with the data in html.
     */
    public static ModelAndView renderForSupplier(Request req, Response res) throws IOException {
        ProductDaoWithJDBC productDaoWithJDBC = new ProductDaoMemWithJDBC();
        SupplierDaoWithJDBC supplierDaoWithJDBC = new SupplierDaoMemWithJDBC();
        ProductCategoryDaoWithJDBC productCategoryDaoWithJDBC = new ProductCategoryDaoMemWithJDBC();

        String selectedSupplier = req.params(":supplierName");

        for (Supplier sup : supplierDaoWithJDBC.getAllSupplier()) {
            if (selectedSupplier.equals(sup.getName())) {
                filteredSupplier = sup;
            }
        }
        Map params = new HashMap<>();
        params.put("supplier", supplierDaoWithJDBC.getAllSupplier());
        params.put("category", productCategoryDaoWithJDBC.getAllCategories());
        params.put("products", productDaoWithJDBC.getProductBy(filteredSupplier));
        logger.info("User has chosen Supplier: {}", filteredSupplier.getName());
        return new ModelAndView(params, "product/index");
    }
}
