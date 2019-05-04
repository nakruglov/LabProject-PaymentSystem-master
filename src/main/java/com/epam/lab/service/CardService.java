package com.epam.lab.service;

import com.epam.lab.core.ProjectContext;
import com.epam.lab.dao.impl.CardDao;
import com.epam.lab.dto.CardDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CardService {
    private static final Logger logger = LogManager.getLogger(CardService.class);
    private static Connection connection;
    private CardDao cardDAO = new CardDao();
    private final DataSource dataSource;
    private final CardDao cardDao;

    public CardService(DataSource dataSource, CardDao cardDao) {
        this.dataSource = dataSource;
        this.cardDao = cardDao;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            logger.debug(e);
        }
    }

    public List<CardDto> findByUserId(int idHolder){

        return cardDAO.findByUserId(connection, idHolder);
    }
}
