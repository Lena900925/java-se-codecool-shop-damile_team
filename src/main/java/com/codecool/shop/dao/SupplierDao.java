package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.util.List;

/**
 * <h1>SupplierDao interface</h1>
 *
 * @author arinyu
 */
public interface SupplierDao {

    void add(Supplier supplier);

    Supplier find(int id);

    void remove(int id);

    List<Supplier> getAll();
}
