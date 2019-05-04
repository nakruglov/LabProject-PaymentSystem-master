package com.epam.lab.servlet;

import com.epam.lab.dto.UserDto;
import com.epam.lab.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/homepage")
public class HomepageServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DO GET HOMEPAGE");
        /*UserService service = new UserService();
        HttpSession session = request.getSession();
        UserDto user = (UserDto) session.getAttribute("user");

        if (user != null &&
                service.authorizationUser(user.getLogin(),user.getPassword())
        ){
            request.getRequestDispatcher("homepage.jsp").forward(request,response);
        } else request.getRequestDispatcher("login.jsp").forward(request,response);*/

        request.getRequestDispatcher("homepage.jsp").forward(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response){

    }
}
