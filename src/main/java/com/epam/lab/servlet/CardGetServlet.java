package com.epam.lab.servlet;

import com.epam.lab.core.ProjectContext;
import com.epam.lab.dto.CardDto;
import com.epam.lab.service.CardService;
import com.epam.lab.service.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/cards")
public class CardGetServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(CardGetServlet.class);

    private CardService cardService = (CardService) ProjectContext.getContext().getContextEntity("cardService");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw =resp.getWriter();
        pw.println("CARD SERVIcE");
        /*int idHolder = Integer.parseInt(req.getParameter("idHolder"));
        List<CardDto> all = cardService.findByUserId(idHolder);
        req.setAttribute("cards", all);
        req.getRequestDispatcher("view/card.jsp").forward(req, resp);*/
    }

}
