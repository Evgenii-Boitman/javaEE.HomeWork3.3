package by.boitman.database.dao;

import by.boitman.database.connection.ConnectionPool;

import by.boitman.database.dto.AccountFilter;
import by.boitman.database.entity.Account;
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
public class AccountDao {
    private static final AccountDao INSTANCE = new AccountDao();
    private static final String SELECT_ALL_ACCOUNT = "SELECT * FROM account";
    private static final String SELECT_ACCOUNT_BY_ID = SELECT_ALL_ACCOUNT + " WHERE id =?";
    private static final String INSERT = "INSERT INTO account (owner_name_account, owner_surname_account, account_number, balance_account) VALUES (?,?,?,?)";
    private static final String DELETE_BY_ID = "DELETE FROM account WHERE id =?";

    private static final String UPDATE_ACCOUNT_BY_ID = "UPDATE account SET owner_name_account = ?, owner_surname_account = ?, account_number = ? WHERE id = ?";
    private static final String SELECT_BY_ACCOUNT = "SELECT * FROM account WHERE balance_account < ? LIMIT ? OFFSET ?";


    public static List<Account> findAllAccount() {
        List<Account> accounts = new ArrayList<>();
        try (Connection connection = ConnectionPool.get();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_ACCOUNT);
            while (resultSet.next()) {
                accounts.add(Account.builder()
                        .id(resultSet.getLong("id"))
                        .ownerNameAccount(resultSet.getString("owner_name_account"))
                        .ownerSurnameAccount(resultSet.getString("owner_surname_account"))
                        .numberAccount(Long.valueOf(resultSet.getString("account_number")))
                        .balanceAccount(Double.valueOf(resultSet.getString("balance_account")))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public static Optional<Account> findByIdAccount(Long id) {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACCOUNT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? Optional.of(Account.builder()
                    .id(resultSet.getLong("id"))
                    .ownerNameAccount(resultSet.getString("owner_name_account"))
                    .ownerSurnameAccount(resultSet.getString("owner_surname_account"))
                    .numberAccount(Long.valueOf(resultSet.getString("account_number")))
                    .balanceAccount(Double.valueOf(resultSet.getString("balance_account")))
                    .build())
                    : Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Account> findByFilterAccount(AccountFilter filterAccount) {
        List<Account> accounts = new ArrayList<>();
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ACCOUNT)) {
            preparedStatement.setDouble(1, filterAccount.balancesAccount());
            preparedStatement.setDouble(2, filterAccount.limit());
            preparedStatement.setDouble(3, filterAccount.limit() * (filterAccount.pageAccount() - 1));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                accounts.add(Account.builder()
                        .id(resultSet.getLong("id"))
                        .ownerNameAccount(resultSet.getString("owner_name_account"))
                        .ownerSurnameAccount(resultSet.getString("owner_surname_account"))
                        .numberAccount(Long.valueOf(resultSet.getString("account_number")))
                        .balanceAccount(Double.valueOf(resultSet.getString("balance_account")))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public Optional<Account> createAccount(Account account) {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, account.getOwnerNameAccount());
            preparedStatement.setString(2, account.getOwnerSurnameAccount());
            preparedStatement.setLong(3, account.getNumberAccount());
            preparedStatement.setDouble(4, account.getBalanceAccount());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                account.setId(generatedKeys.getLong("id"));
            }
            return Optional.of(account);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


    public Optional<Account> updateAccount(Account account) {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT_BY_ID)) {
            preparedStatement.setString(1, account.getOwnerNameAccount());
            preparedStatement.setString(2, account.getOwnerSurnameAccount());
            preparedStatement.setLong(3, account.getNumberAccount());
            preparedStatement.setDouble(4, account.getBalanceAccount());
            preparedStatement.setLong(5, account.getId());
            preparedStatement.executeUpdate();
            return Optional.of(account);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public boolean deleteAccount(Long id) {
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

    public static AccountDao getInstance() {
        return INSTANCE;
    }
}
