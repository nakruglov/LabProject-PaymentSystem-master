package com.epam.lab.servlet;

import com.epam.lab.dto.UserDto;
import com.epam.lab.service.EncryptPassword;
import com.epam.lab.service.UserService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/reg")
public class RegistrationServlet extends HttpServlet {
    private UserService service;

    @Override
    public void init() throws ServletException {
        service = new UserService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("reg.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String repeatpassword = request.getParameter("repeatpassword");

        password = EncryptPassword.hashPassword(password,"P@ssS@lt").toString();
        repeatpassword = EncryptPassword.hashPassword(repeatpassword,"P@ssS@lt").toString();

        UserDto user = UserDto.builder()
                .setFirstName(firstname)
                .setLastName(lastname)
                .setLogin(login)
                .setPassword(password)
                .setRole(1)
                .build();

        if(!service.isLoginExist(login) && repeatpassword.equals(password)) {
            user = service.addUserToDb(user);
            if (user != null){
                session.setAttribute("user", user);
                request.getRequestDispatcher("homepage.jsp").forward(request, response);
            }
        }

        request.getRequestDispatcher("reg.jsp").forward(request, response);
    }
}
