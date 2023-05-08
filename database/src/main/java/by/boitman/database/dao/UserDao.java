package by.boitman.database.dao;

import by.boitman.database.connection.ConnectionPool;
import by.boitman.database.entity.User;
import by.boitman.database.entity.enam.Gender;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class UserDao {
    private static final UserDao INSTANCE = new UserDao();

    private static final String SELECT_ALL_USER = "SELECT * FROM users";
    private static final String SELECT_USER_BY_ID = SELECT_ALL_USER + " WHERE id =?";
    private static final String SELECT_BY_EMAIL_PASS = "SELECT * FROM users WHERE email =? AND passwords = ?";

    private static final String INSERT_USER = "INSERT INTO users (names, surname, email, passwords, gender, roles) VALUES (?,?,?,?,?,?)";
    private static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id =?";
    private static final String UPDATE_USER_BY_ID = "UPDATE users SET names = ?, surname = ?, email = ?, passwords = ? WHERE id = ?";


    public Optional<User> getByEmailAndPass(String email, String password) {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL_PASS)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? Optional.of(User.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("names"))
                    .surname(resultSet.getString("surname"))
                    .email(resultSet.getString("email"))
                    .build())
                    : Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static List<User> findAllUser() {
        List<User> user = new ArrayList<>();
        try (Connection connection = ConnectionPool.get();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_USER);
            while (resultSet.next()) {
                user.add(User.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("names"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .gender(Gender.valueOf(resultSet.getString("gender")))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static Optional<User> findByIdUser(Long id) {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? Optional.of(User.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("names"))
                    .surname(resultSet.getString("surname"))
                    .email(resultSet.getString("email"))
                    .gender(Gender.valueOf(resultSet.getString("gender")))
                    .build())
                    : Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<User> createUser(User user) {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getGender().name());
            preparedStatement.setString(6, user.getRole().name());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong("id"));
            }
            return Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    public Optional<User> updateUser(User user) {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BY_ID)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
            return Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    public boolean deleteUser(Long id) {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

}
