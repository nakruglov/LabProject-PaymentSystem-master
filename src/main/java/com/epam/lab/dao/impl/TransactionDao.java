package com.epam.lab.dao.impl;

import com.epam.lab.dao.CrudDao;
import com.epam.lab.dto.TransactionDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao implements CrudDao<TransactionDto> {

    private static final Logger logger = LogManager.getLogger(TransactionDao.class);
    @Override
    public List<TransactionDto> findAll(Connection connection) {
        String sql = "SELECT * FROM transactions;";
        List<TransactionDto> list = new ArrayList<>();
        try (Statement stm = connection.createStatement();
             ResultSet resultSet = stm.executeQuery(sql)) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Integer idSourceCard = resultSet.getInt("id_sourcecard");
                Integer idDestinationCard = resultSet.getInt("id_destinationcard");
                Timestamp timestamp = resultSet.getTimestamp("timestamp");
                Integer amount = resultSet.getInt("amount");
                TransactionDto transactionDTO = new TransactionDto.Builder()
                        .setId(id)
                        .setIdSourceCard(idSourceCard)
                        .setIdDestinationCard(idDestinationCard)
                        .setTimestamp(timestamp)
                        .setAmount(amount)
                        .build();
                list.add(transactionDTO);
            }
        } catch (SQLException e) {
            logger.debug("TransactionDto getAll error.");
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public TransactionDto find(Connection connection, int id) {
        TransactionDto transactionDTO = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM transactions WHERE id=?;")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next())
                    return null;
                int transactionId = resultSet.getInt("id");
                int idSourceCard = resultSet.getInt("id_sourcecard");
                int idDestinationCard = resultSet.getInt("id_destinationcard");
                Timestamp timestamp = resultSet.getTimestamp("timestamp");
                int amount = resultSet.getInt("amount");

                return TransactionDto.builder()
                        .setId(transactionId)
                        .setIdSourceCard(idSourceCard)
                        .setIdDestinationCard(idDestinationCard)
                        .setTimestamp(timestamp)
                        .setAmount(amount)
                        .build();
            }
        } catch (SQLException e) {
            logger.debug("TransactionDto read error.");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TransactionDto insert(Connection connection, TransactionDto transactionDTO) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO transactions (id_sourcecard, id_destinationcard, timestamp, amount) VALUES (?,?,?,?);",
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, transactionDTO.getIdSourceCard());
            statement.setInt(2, transactionDTO.getIdDestinationCard());
            statement.setTimestamp(3, transactionDTO.getTimestamp());
            statement.setInt(4, transactionDTO.getAmount());
            statement.executeUpdate();

            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                resultSet.next();
                return TransactionDto.builder()
                        .setId(resultSet.getInt(1))
                        .setIdSourceCard(transactionDTO.getIdSourceCard())
                        .setIdDestinationCard(transactionDTO.getIdDestinationCard())
                        .setTimestamp(transactionDTO.getTimestamp())
                        .setAmount(transactionDTO.getAmount())
                        .build();
            }
        } catch (SQLException e) {
            logger.debug("TransactionDto create error.");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TransactionDto update(Connection connection, TransactionDto transactionDTO) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE transactions SET id_sourcecard=?, id_destinationcard=?, timestamp=?, amount=? WHERE id=?;")) {
            statement.setInt(1, transactionDTO.getIdSourceCard());
            statement.setInt(2, transactionDTO.getIdDestinationCard());
            statement.setTimestamp(3, transactionDTO.getTimestamp());
            statement.setInt(4, transactionDTO.getAmount());
            statement.setInt(5, transactionDTO.getId());
            statement.executeUpdate();

            return transactionDTO;
        } catch (SQLException e) {
            logger.debug("TransactionDto update error.");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Connection connection, TransactionDto transactionDTO) {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM transactions WHERE id=?;")) {
            statement.setInt(1, transactionDTO.getId());
            statement.execute();
        } catch (SQLException e) {
            logger.debug("TransactionDto delete error.");
            e.printStackTrace();
        }
    }
}

