package com.epam.lab.service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import com.epam.lab.dao.impl.AccountDao;
import com.epam.lab.dao.impl.CardDao;
import com.epam.lab.dao.impl.TransactionDao;
import com.epam.lab.dto.AccountDto;
import com.epam.lab.dto.CardDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TransactionService {
    private static final Logger logger = LogManager.getLogger(TransactionService.class);
    private final DataSource dataSource;
    private final TransactionDao transactionDao;
    private final CardDao cardDao;
    private final AccountDao accountDao;
    Connection connection;

    public TransactionService(DataSource dataSource, TransactionDao transactionDao, CardDao cardDao, AccountDao accountDao) {
        this.dataSource=dataSource;
        this.transactionDao=transactionDao;
        this.cardDao = cardDao;
        this.accountDao = accountDao;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            logger.debug(e);
        }
    }

    public List<CardDto> getActiveCardsByUserId(int userId) {
        List<CardDto> cards = cardDao.findActiveByUserId(connection,userId);
        return cards;
    }

    public boolean tryProcessTransaction(Integer sourceCard, Integer destinationCard, Integer amount) throws SQLException {
        CardDto srcCardDTO = cardDao.find(connection,sourceCard);
        CardDto destCardDTO = cardDao.find(connection,destinationCard);
        if(destCardDTO==null) return false;
        //TODO dest card validation - may be handling exception from dao

        AccountDto srcAccountDTO = accountDao.find(connection,srcCardDTO.getIdAccount());
        AccountDto destAccountDTO = accountDao.find(connection,destCardDTO.getIdAccount());

        Long moneyOnSrcAccount = srcAccountDTO.getBalance();
        Long moneyOnDestAccount = destAccountDTO.getBalance();

        if((moneyOnSrcAccount - amount)>=0){
            connection.setAutoCommit(false);
            try
            {
                //TODO get exception from DAO to handle here
                srcAccountDTO.setBalance(moneyOnSrcAccount - amount);
                destAccountDTO.setBalance(moneyOnDestAccount + amount);

                accountDao.update(connection,srcAccountDTO);
                accountDao.update(connection,destAccountDTO);
            }
            catch(Exception ex)
            {
                connection.rollback();
                connection.setAutoCommit(true);
                logger.debug(ex.getMessage());
                return false;
            }
            connection.commit();
            connection.setAutoCommit(true);
        }
        return  true;
    }
}
