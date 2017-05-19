package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderableDao;
import com.codecool.shop.model.Orderable;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>OrderableDaoMem class</h1> for storing data. <p>Implements the OrderableDao interface.</p><p>It is not used just prepared for the shopping cart.</p>
 *
 * @author arinyu
 */
public class OrderableDaoMem implements OrderableDao {

    private List<Orderable> DATA = new ArrayList<>();
    private static OrderableDaoMem instance = null;

    private OrderableDaoMem() {
    }

    /**
     * If there is no instance, it creates one. If it exist, it returns it.
     *
     * @return instance
     */
    public static OrderableDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderableDaoMem();
        }
        return instance;
    }

    /**
     * Add the category of the Orderable
     *
     * @param category Product category
     */
    @Override
    public void add(Orderable category) {
        category.setId(DATA.size() + 1);
        DATA.add(category);
    }

    /**
     * Finds ordered product
     *
     * @param id ID of the ordered product
     * @return data with the given id
     */
    @Override
    public Orderable find(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID must equal or bigger than 0");
        }
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    /**
     * Removes product with the given id
     *
     * @param id ID of the removable ordered product
     */
    @Override
    public void remove(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID must equal or bigger than 0");
        }
        DATA.remove(find(id));
    }

    /**
     * Lists all ordered products
     *
     * @return a List of the ordered products
     */
    @Override
    public List<Orderable> getAll() {
        return DATA;
    }

    public boolean order() {
        System.out.println("ordered");
        return true;
    }
}