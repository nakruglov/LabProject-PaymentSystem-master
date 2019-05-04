package com.epam.lab.servlet;

import com.epam.lab.core.ProjectContext;
import com.epam.lab.dto.CardDto;
import com.epam.lab.dto.UserDto;
import com.epam.lab.service.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/transaction")
public class TransactionsServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(TransactionsServlet.class);

    private TransactionService transactionService = (TransactionService) ProjectContext.getContext().getContextEntity("transactionService");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("DO GET IN TRANSACTION");
        //TODO get/send user id from home page
        HttpSession session = request.getSession();
        UserDto user = (UserDto) session.getAttribute("user");
        List<CardDto> cards = transactionService.getActiveCardsByUserId(user.getId());

        response.setContentType("text/html");
        request.setAttribute("cardsList", cards);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/transaction.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.debug(e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Integer srdCard,destCard,resolvedAmount;
        srdCard = Integer.valueOf(request.getParameter("sourceCard"));
        destCard = Integer.valueOf(request.getParameter("destinationCard"));
        Double amount = Double.valueOf(request.getParameter("amount"));
        resolvedAmount = Math.toIntExact(Math.round(amount * 100));

        try {
            if(transactionService.tryProcessTransaction(srdCard,destCard,resolvedAmount)){
                request.setAttribute("resultInfo","Transaction has finished successfully");
            }else{
                request.setAttribute("resultInfo","Transaction has failed");
            }
        } catch (SQLException e) {
            logger.debug(e.getMessage());
            request.setAttribute("resultInfo","Transaction has failed");
        }
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/transaction.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.debug(e.getMessage());
        }
    }
}
