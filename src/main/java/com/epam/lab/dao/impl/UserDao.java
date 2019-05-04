/* Users data access object */

package com.epam.lab.dao.impl;

import com.epam.lab.dao.CrudDao;
import com.epam.lab.dto.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements CrudDao<UserDto> {

    private static final Logger logger = LogManager.getLogger(UserDao.class);
    public List<UserDto> findAll(Connection connection) {
        List<UserDto> list = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users;")) {
            while (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                Integer role = resultSet.getInt("role");
                Integer id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");

                UserDto user = UserDto.builder()
                        .setId(id)
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setLogin(login)
                        .setPassword(password)
                        .setRole(role)
                        .build();
                list.add(user);
            }
        } catch (SQLException e) {
            logger.debug("Some errors when try to get all users!");
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public UserDto find(Connection connection, int id) {
        throw new RuntimeException("Method not supported");
    }

    public UserDto findByLogin(Connection connection, String login){
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM users WHERE login=?;")){
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            return UserDto.builder()
                    .setFirstName(resultSet.getString("firstname"))
                    .setLastName(resultSet.getString("lastname"))
                    .setRole(resultSet.getInt("role"))
                    .setId(resultSet.getInt("id"))
                    .setLogin(resultSet.getString("login"))
                    .setPassword(resultSet.getString("password"))
                    .build();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isLoginExist(Connection connection, String login){
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT COUNT(login)=1 FROM users WHERE login=?;")){
            statement.setString(1,login);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getBoolean(1);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public UserDto insert(Connection connection, UserDto user) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO users (firstName, lastname, role, login, password) VALUES (?,?,?,?,?);",
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setInt(3, user.getRole());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());
            statement.executeUpdate();

            try(ResultSet resultSet = statement.getGeneratedKeys()){
                resultSet.next();

                return UserDto.builder()
                        .setFirstName(user.getFirstName())
                        .setLastName(user.getLastName())
                        .setRole(user.getRole())
                        .setId(resultSet.getInt("id"))
                        .setLogin(user.getLogin())
                        .setPassword(user.getPassword())
                        .build();
            }

        } catch (SQLException e) {
            logger.debug("Some errors when try to insert data!");
            e.printStackTrace();
        }
        return null;
    }

    public UserDto update(Connection connection, UserDto user) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE users set firstname=?, lastname=?, login=?, password=? WHERE id=?;")) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getId());
            statement.executeUpdate();

            return user;
        } catch (SQLException e) {
            logger.debug("Some errors when try to update data!");
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Connection connection, UserDto user) {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM users WHERE id=?;")) {
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.debug("Some errors when try to delete data!");
            e.printStackTrace();
        }
    }
}
