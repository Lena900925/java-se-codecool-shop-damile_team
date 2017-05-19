package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.io.IOException;
import java.util.List;

/**
 * <h1>SupplierDaoWithJDBC interface</h1> uses JDBC
 *
 * @author arinyu
 */
public interface SupplierDaoWithJDBC {

    List<Supplier> getAllSupplier() throws IOException;

    Supplier findSupplier(int id);

    void add(Supplier supplier);
}