package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>SupplierDaoMem class</h1> for storing suppliers. <p>Implements the SupplierDao interface.</p>
 *
 * @author arinyu
 */
public class SupplierDaoMem implements SupplierDao {

    private List<Supplier> DATA = new ArrayList<>();
    private static SupplierDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoMem() {
    }

    /**
     * If there is no instance, it creates one. If it exist, it returns it.
     *
     * @return instance
     */
    public static SupplierDaoMem getInstance() {
        if (instance == null) {
            instance = new SupplierDaoMem();
        }
        return instance;
    }

    /**
     * Adds a supplier
     *
     * @param supplier Supplier supplier
     */
    @Override
    public void add(Supplier supplier) {
        supplier.setId(DATA.size() + 1);
        DATA.add(supplier);
    }

    /**
     * Finds the supplier
     *
     * @param id ID of the supplier
     * @return data with the given id
     */
    @Override
    public Supplier find(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID not good");
        }
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    /**
     * Removes product with the given id
     *
     * @param id ID of the supplier to be removed
     */
    @Override
    public void remove(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID not good");
        }
        DATA.remove(find(id));
    }

    /**
     * Lists all suppliers
     *
     * @return List of suppliers
     */
    @Override
    public List<Supplier> getAll() {
        return DATA;
    }
}
