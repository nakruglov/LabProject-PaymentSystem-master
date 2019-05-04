package com.epam.lab.dao.impl;

import com.epam.lab.dao.CrudDao;
import com.epam.lab.dto.AccountDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountDao implements CrudDao<AccountDto> {

    private static final Logger logger = LogManager.getLogger(AccountDao.class);
    @Override
    public List<AccountDto> findAll(Connection connection) {
        List<AccountDto> list = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM accounts;")) {

            while (resultSet.next()) {
                long balance = resultSet.getLong("balance");
                int idHolder = resultSet.getInt("id_holder");
                int status = resultSet.getInt("status");
                int id = resultSet.getInt("id");

                AccountDto account = AccountDto.builder()
                        .setId(id)
                        .setStatus(status)
                        .setIdHolder(idHolder)
                        .setBalance(balance)
                        .build();

                list.add(account);
            }
        } catch (SQLException e) {
            logger.debug(e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public AccountDto find(Connection connection, int id) {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM accounts WHERE id=?;")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return AccountDto.builder()
                        .setId(id)
                        .setStatus(resultSet.getInt("status"))
                        .setIdHolder(resultSet.getInt("id_holder"))
                        .setBalance(resultSet.getLong("balance"))
                        .build();
            }
        } catch (SQLException e) {
            logger.debug(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AccountDto insert(Connection connection, AccountDto account) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO accounts (status, id_holder, balance) VALUES (?,?,?);",
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, account.getStatus());
            statement.setInt(2, account.getIdHolder());
            statement.setLong(3, account.getBalance());

            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                resultSet.next();
                return AccountDto.builder()
                        .setId(resultSet.getInt(1))
                        .setStatus(account.getStatus())
                        .setIdHolder(account.getIdHolder())
                        .setBalance(account.getBalance())
                        .build();
            }
        } catch (SQLException e) {
            logger.debug(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AccountDto update(Connection connection, AccountDto account) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE accounts SET status=?, id_holder=?, balance=? WHERE id=?;")) {
            statement.setInt(1, account.getStatus());
            statement.setInt(2, account.getIdHolder());
            statement.setLong(3, account.getBalance());
            statement.setInt(4, account.getId());
            statement.executeUpdate();
            return account;
        } catch (SQLException e) {
            logger.debug(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Connection connection, AccountDto account) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM accounts WHERE id=?;")) {
            statement.setInt(1, account.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
            e.printStackTrace();
        }
    }

}