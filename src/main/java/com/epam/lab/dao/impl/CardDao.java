package com.epam.lab.dao.impl;

import com.epam.lab.dao.CrudDao;
import com.epam.lab.dto.CardDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardDao implements CrudDao<CardDto> {
    private static final Logger logger = LogManager.getLogger(CardDao.class);

    @Override
    public List<CardDto> findAll(Connection connection) {

        List<CardDto> list = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM cards;");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idHolder = resultSet.getInt("id_holder");
                int status = resultSet.getInt("status");
                int idAccount = resultSet.getInt("id_account");

                CardDto card = CardDto.builder()
                        .setId(id)
                        .setIdHolder(idHolder)
                        .setStatus(status)
                        .setIdAccount(idAccount).build();

                list.add(card);
            }
        } catch (SQLException e) {
            logger.debug(e);
        }

        return list;
    }

    @Override
    public CardDto find(Connection connection, int id) {

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM cards WHERE id = ?;")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return CardDto.builder()
                        .setId(id)
                        .setIdHolder(resultSet.getInt("id_holder"))
                        .setStatus(resultSet.getInt("status"))
                        .setIdAccount(resultSet.getInt("id_account"))
                        .build();
            } catch (SQLException e) {
                logger.debug(e+"There is no such id in DB!");
                throw new IllegalArgumentException("There is no such id in DB!");
            }
        } catch (SQLException e) {
            logger.debug(e);
        }
        return null;
    }

    @Override
    public CardDto insert(Connection connection, CardDto card){

        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT cards (id_holder, status, id_account) VALUES (?,?,?);",
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, card.getIdHolder());
            statement.setInt(2, card.getStatus());
            statement.setInt(3, card.getIdAccount());

            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                resultSet.next();
                return CardDto.builder()
                        .setId(resultSet.getInt(1))
                        .setStatus(card.getIdHolder())
                        .setIdHolder(card.getStatus())
                        .build();
            }
        } catch (SQLException e) {
            logger.debug(e);
        }
        return null;
    }

    @Override
    public CardDto update(Connection connection, CardDto card) {

        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE accounts SET id_holder=?, status=?, id_account=? WHERE id=?;")) {
            statement.setInt(1, card.getIdHolder());
            statement.setInt(2, card.getStatus());
            statement.setInt(3, card.getIdAccount());
            statement.executeUpdate();
            return card;
        } catch (SQLException e) {
            logger.debug(e);
        }
        return null;
    }

    @Override
    public void delete(Connection connection, CardDto card) {

        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM cards WHERE id=?;")) {
            statement.setInt(1, card.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e);
        }
    }


    public List<CardDto> findByUserId(Connection connection, int idHolder) {

        List<CardDto> cards = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM cards WHERE id_holder = ?;")) {
            statement.setInt(1, idHolder);
            ResultSet resultSet = statement.executeQuery();

            CardDto cardDTO;

            while (resultSet.next()) {
                cardDTO = CardDto.builder()
                        .setId(resultSet.getInt("id"))
                        .setIdHolder(resultSet.getInt("id_holder"))
                        .setStatus(resultSet.getInt("status"))
                        .setIdAccount(resultSet.getInt("id_account"))
                        .build();

                cards.add(cardDTO);
            }
        } catch (SQLException e) {
            logger.debug(e);
        }

        return cards;
    }

    public List<CardDto> findActiveByUserId(Connection connection, int userId) {

        List<CardDto> cards = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM cards WHERE id_holder = ? and status = 1;")) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            CardDto cardDTO;

            while (resultSet.next()) {
                cardDTO = CardDto.builder()
                        .setId(resultSet.getInt("id"))
                        .setIdHolder(resultSet.getInt("id_holder"))
                        .setStatus(resultSet.getInt("status"))
                        .setIdAccount(resultSet.getInt("id_account"))
                        .build();

                cards.add(cardDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cards;
    }
}
