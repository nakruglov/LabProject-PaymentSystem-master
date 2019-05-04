package com.epam.lab.service;

import com.epam.lab.core.ProjectContext;
import com.epam.lab.dao.impl.UserDao;
import com.epam.lab.dto.UserDto;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class UserService {

    private UserDao dao = new UserDao();
    private ProjectContext context = ProjectContext.getContext();
    private DataSource dataSource = (DataSource) context.getContextEntity("dataSource");

    public UserDto findByLogin(String login){
        try(Connection connection = dataSource.getConnection()) {
            UserDto user = dao.findByLogin(connection, login);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isLoginExist(String login){
        try (Connection connection = dataSource.getConnection()) {
            return dao.isLoginExist(connection, login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Make a decision, let user in or not

    public boolean authorizationUser(String login, String password){
        try(Connection connection = dataSource.getConnection()){
            UserDto user = dao.findByLogin(connection,login);
            if (user != null){
                return user.getPassword().equals(password);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public UserDto addUserToDb(UserDto user){
        UserDto responseUser = null;

        try(Connection connection = dataSource.getConnection()) {
            responseUser = dao.insert(connection, user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return responseUser;
    }


}
