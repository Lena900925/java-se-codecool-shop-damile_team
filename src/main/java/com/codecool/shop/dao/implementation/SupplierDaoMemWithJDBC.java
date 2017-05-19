package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDaoWithJDBC;
import com.codecool.shop.dao.SupplierDaoWithJDBC;
import com.codecool.shop.model.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.List;

/**
 * <h1>SupplierDaoMemWithJDBC class</h1> inherits from JDBCConnection
 *
 * @author arinyu
 */
public class SupplierDaoMemWithJDBC extends JDBCConnection implements SupplierDaoWithJDBC {

    public SupplierDaoMemWithJDBC() throws IOException {
    }

    /**
     * Lists all suppliers from the SQL database
     *
     * @throws IOException If something went wrong
     * @return List of all suppliers
     */
    @Override
    public List<Supplier> getAllSupplier() throws IOException {
        String query = "SELECT * FROM suppliers;";
        ProductDaoWithJDBC productDaoWithJDBC = new ProductDaoMemWithJDBC();
        List<Product> products = productDaoWithJDBC.listAllProducts();

        List<Supplier> resultList = new ArrayList<>();

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Supplier supp = new Supplier(
                        resultSet.getInt("supplierId"),
                        resultSet.getString("name"),
                        resultSet.getString("description")
                );
                for (Product prod : products) {
                    if (prod.getSupplier().getSupplierId().equals(supp.getSupplierId())) {
                        supp.addProduct(prod);
                    }
                }
                resultList.add(supp);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * Finds suppliers by the given id from the SQL database
     *
     * @return List of the filtered suppliers
     */
    @Override
    public Supplier findSupplier(int id) {
        String query = "SELECT * FROM suppliers WHERE supplierId ='" + id + "';";
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                Supplier supplier = new Supplier(
                        resultSet.getInt(1),
                        resultSet.getString("name"),
                        resultSet.getString("description")
                );
                connection.close();
                return supplier;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Adds new supplier into SQL database
     *
     * @param supplier Supplier supplier
     */
    @Override
    public void add(Supplier supplier) {
        String query = "INSERT INTO suppliers (supplierId, name, description)" +
                "VALUES ('" + supplier.getSupplierId() + "', '" + supplier.getName() + "','" + supplier.getDescription() + "');";
        executeQuery(query);
    }

}