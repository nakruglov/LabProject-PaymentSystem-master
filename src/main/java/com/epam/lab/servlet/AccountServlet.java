package com.epam.lab.servlet;

import com.epam.lab.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/accounts")
public class AccountServlet extends HttpServlet {
    final static String accountsAddress = "WEB-INF/account.jsp";

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idList = Integer.parseInt(req.getParameter("idacc"));

        System.out.println ("========= " + idList);
        AccountService as = new AccountService();

        req.setAttribute("acc", as.find (idList));
        req.setAttribute("accountList", as.findAll ());

        req.getRequestDispatcher(accountsAddress).forward(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("accountList", new AccountService().findAll());

        req.getRequestDispatcher(accountsAddress).forward(req, resp);
    }
}
