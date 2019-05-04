package com.epam.lab.dao;

import java.sql.Connection;
import java.util.List;

public interface CrudDao<T> {
    List<T> findAll(Connection connection);
    T find(Connection connection, int id);
    T insert(Connection connection, T t);
    T update(Connection connection, T t);
    void delete(Connection connection, T t);
}