package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDaoWithJDBC;
import com.codecool.shop.dao.ProductDaoWithJDBC;
import com.codecool.shop.model.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * <h1>ProductCategoryDaoMemWithJDBC class</h1> inherits from JDBCConnection
 *
 * @author arinyu
 */
public class ProductCategoryDaoMemWithJDBC extends JDBCConnection implements ProductCategoryDaoWithJDBC {

    public ProductCategoryDaoMemWithJDBC() throws IOException {
    }

    /**
     * Lists all product categories from the SQL database
     *
     * @throws IOException If something went wrong
     * @return List of all product categories
     */
    @Override
    public List<ProductCategory> getAllCategories() throws IOException {
        String query = "SELECT * FROM productcategories;";
        List<ProductCategory> resultList = new ArrayList<>();
        ProductDaoWithJDBC productDaoWithJDBC = new ProductDaoMemWithJDBC();
        List<Product> products = productDaoWithJDBC.listAllProducts();

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ProductCategory prodCat = new ProductCategory(
                        resultSet.getInt("productCategoryId"),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description")
                );
                for (Product prod : products) {
                    if (prod.getProductCategory().getProductCategoryId().equals(prodCat.getProductCategoryId())) {
                        prodCat.addProduct(prod);
                    }
                }
                resultList.add(prodCat);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * Finds product categories by the given id from the SQL database
     *
     * @return List of the filtered product categories
     */
    @Override
    public ProductCategory findCategory(int id) {
        String query = "SELECT * FROM productcategories WHERE productCategoryId ='" + id + "';";

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                ProductCategory prodCat = new ProductCategory(
                        resultSet.getInt(1),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description")
                );
                connection.close();
                return prodCat;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Finds product categories by the given name from the SQL database
     *
     * @return List of filtered product categories
     */
    @Override
    public ProductCategory findCategory(String name) {
        String query = "SELECT * FROM productcategories WHERE name ='" + name + "';";

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                ProductCategory prodCat = new ProductCategory(
                        resultSet.getInt(1),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description")
                );
                connection.close();
                return prodCat;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Adds new product category into SQL database
     *
     * @param prodCat Product Category
     */
    @Override
    public void add(ProductCategory prodCat) {
        String query = "INSERT INTO productcategories (productCategoryId, name, department, description)" +
                "VALUES ('" + prodCat.getProductCategoryId() + "', '" + prodCat.getName() + "', '" + prodCat.getDepartment() + "', '" + prodCat.getDescription() + "');";
        executeQuery(query);
    }

}