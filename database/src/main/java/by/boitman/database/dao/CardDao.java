package by.boitman.database.dao;

import by.boitman.database.connection.ConnectionPool;
import by.boitman.database.dto.CardFilter;
import by.boitman.database.entity.Card;
import lombok.NoArgsConstructor;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CardDao {
    private static final CardDao INSTANCE = new CardDao();
    private static final String SELECT_ALL_CARD = "SELECT * FROM card";
    private static final String SELECT_CARD_BY_ID = SELECT_ALL_CARD + " WHERE id =?";
    private static final String INSERT = "INSERT INTO card (owner_name, owner_surname, date_card, card_number, balance) VALUES (?,?,?,?,?)";
    private static final String DELETE_BY_ID = "DELETE FROM card WHERE id =?";

    private static final String UPDATE_CARD_BY_ID = "UPDATE card SET owner_name = ?, owner_surname = ?, date_card = ?, card_number = ? WHERE id = ?";
    private static final String SELECT_BY_CARD = "SELECT * FROM card WHERE balance < ? LIMIT ? OFFSET ?";


    public static List<Card> findAll() {
        List<Card> cards = new ArrayList<>();
        try (Connection connection = ConnectionPool.get();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_CARD);
            while (resultSet.next()) {
                cards.add(Card.builder()
                        .id(resultSet.getLong("id"))
                        .ownerName(resultSet.getString("owner_name"))
                        .ownerSurname(resultSet.getString("owner_surname"))
                        .dateCard(resultSet.getString("date_card"))
                        .cardNumber(Long.valueOf(resultSet.getString("card_number")))
                        .balance(Double.valueOf(resultSet.getString("balance")))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }

    public static Optional<Card> findById(Long id) {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CARD_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? Optional.of(Card.builder()
                    .id(resultSet.getLong("id"))
                    .ownerName(resultSet.getString("owner_name"))
                    .ownerSurname(resultSet.getString("owner_surname"))
                    .dateCard(resultSet.getString("date_card"))
                    .cardNumber(Long.valueOf(resultSet.getString("card_number")))
                    .balance(Double.valueOf(resultSet.getString("balance")))
                    .build())
                    : Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Card> findByFilter(CardFilter filter) {
        List<Card> cards = new ArrayList<>();
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_CARD)) {
            preparedStatement.setDouble(1, filter.balance());
            preparedStatement.setDouble(2, filter.limit());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cards.add(Card.builder()
                        .id(resultSet.getLong("id"))
                        .ownerName(resultSet.getString("owner_name"))
                        .ownerSurname(resultSet.getString("owner_surname"))
                        .dateCard(resultSet.getString("date_card"))
                        .cardNumber(Long.valueOf(resultSet.getString("card_number")))
                        .balance(Double.valueOf(resultSet.getString("balance")))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }

    public Optional<Card> create(Card card) {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, card.getOwnerName());
            preparedStatement.setString(2, card.getOwnerSurname());
            preparedStatement.setDate(3, Date.valueOf(card.getDateCard()));
            preparedStatement.setLong(4, card.getCardNumber());
            preparedStatement.setDouble(5, card.getBalance());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                card.setId(generatedKeys.getLong("id"));
            }
            return Optional.of(card);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<Card> update(Card card) {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CARD_BY_ID)) {
            preparedStatement.setString(1, card.getOwnerName());
            preparedStatement.setString(2, card.getOwnerSurname());
            preparedStatement.setDate(3, Date.valueOf(card.getDateCard()));
            preparedStatement.setLong(4, card.getCardNumber());
            preparedStatement.setDouble(5, card.getBalance());
            preparedStatement.setLong(6, card.getId());
            preparedStatement.executeUpdate();
            return Optional.of(card);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public boolean delete(Long id) {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static CardDao getInstance() {
        return INSTANCE;
    }
}

