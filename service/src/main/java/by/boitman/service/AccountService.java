package by.boitman.service;

import by.boitman.service.dao.AccountDao;
import by.boitman.service.dto.AccountFilter;
import by.boitman.service.entity.Account;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class AccountService {
    private static final AccountService INSTANCE = new AccountService();
    private static final AccountDao ACCOUNT_DAO = AccountDao.getInstance();

    public List<Account> getAllAccount() {
        return AccountDao.findAllAccount();
    }
    public List<Account> getFindByFilterAccount(AccountFilter filterAccount) {
        return ACCOUNT_DAO.findByFilterAccount(filterAccount);
    }

    public static Account getByIdAccount(Long id) {
        return ACCOUNT_DAO.findByIdAccount(id)
                .orElse(Account.builder()
                        .ownerNameAccount("Данный пользователь отсутствует в базе")
                        .build());
    }
    public Optional<Account> save(Account account) {
        return ACCOUNT_DAO.createAccount(account);
    }
    public Optional<Account> createAccount(Account account) {
        return ACCOUNT_DAO.createAccount(account);
    }
    public static AccountService getInstance() {
        return INSTANCE;
    }
}
