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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService service;

    @Override
    public void init() throws ServletException {
        service = new UserService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        UserDto user = (UserDto) session.getAttribute("user");

        if (user != null &&
                service.authorizationUser(user.getLogin(),user.getPassword())
        ){
            request.getRequestDispatcher("homepage.jsp").forward(request,response);
        } else request.getRequestDispatcher("login.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        password = EncryptPassword.hashPassword(password,"P@ssS@lt").toString();

        HttpSession session = request.getSession();
        UserDto user = service.findByLogin(login);

        if (service.authorizationUser(login,password)){
            session.setAttribute("user",user);
            request.getRequestDispatcher("homepage.jsp").forward(request,response);
        }else request.getRequestDispatcher("login.jsp").forward(request,response);
    }
}
