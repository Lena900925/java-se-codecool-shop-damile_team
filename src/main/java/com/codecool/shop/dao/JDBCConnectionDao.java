package com.codecool.shop.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * <h1>Connection interface</h1> for the database connection.
 *
 * @author arinyu
 *
 */
public interface JDBCConnectionDao {

    Connection getConnection() throws SQLException;
    void executeQuery(String query);
    String getDbData(int textLine) throws IOException;
}