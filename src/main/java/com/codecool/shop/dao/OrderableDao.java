package com.codecool.shop.dao;

import com.codecool.shop.model.Orderable;

import java.util.List;

/**
 * <h1>Orderable class</h1> is not used but prepared for the Shopping Cart.
 *
 * @author arinyu
 */
public interface OrderableDao {

    void add(Orderable orderable);

    Orderable find(int id);

    void remove(int id);

    List<Orderable> getAll();

    boolean order();
}