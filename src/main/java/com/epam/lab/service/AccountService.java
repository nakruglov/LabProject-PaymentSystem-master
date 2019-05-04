package com.epam.lab.service;

import com.epam.lab.core.ProjectContext;
import com.epam.lab.dao.impl.AccountDao;
import com.epam.lab.dto.AccountDto;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AccountService {
    private final static AccountDao accountDao = new AccountDao();

    private Connection getConnection() {

        ProjectContext context = ProjectContext.getContext();
        DataSource dataSource = (DataSource) context.getContextEntity("dataSource");

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException();
    }

    public List<AccountDto> findAll() {
        Connection connection = getConnection();
        return accountDao.findAll(connection);
    }

    public AccountDto find(int id) {
        Connection connection = getConnection();
        return accountDao.find(connection, id);
    }

    public AccountDto update(AccountDto accountDto) {
        Connection connection = getConnection();
        return accountDao.update(connection, accountDto);
    }

    public AccountDto insert(AccountDto accountDto) {
        Connection connection = getConnection();
        return accountDao.insert(connection, accountDto);
    }

    public void delete(AccountDto accountDto) {
        Connection connection = getConnection();
        accountDao.delete(connection, accountDto);
    }
}
