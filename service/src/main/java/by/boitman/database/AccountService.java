package by.boitman.database;

import by.boitman.database.dao.AccountDao;
import by.boitman.database.dto.AccountFilter;
import by.boitman.database.entity.Account;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class AccountService {
    private static final AccountService INSTANCE = new AccountService();
    private static final AccountDao accountDao = AccountDao.getInstance();

    public List<Account> getAllAccount() {
        return AccountDao.findAllAccount();
    }
    public List<Account> getFindByFilterAccount(AccountFilter filterAccount) {
        return accountDao.findByFilterAccount(filterAccount);
    }

    public static Account getByIdAccount(Long id) {
        return accountDao.findByIdAccount(id)
                .orElse(Account.builder()
                        .ownerNameAccount("Данный пользователь отсутствует в базе")
                        .build());
    }
    public Optional<Account> save(Account account) {
        return accountDao.createAccount(account);
    }
    public Optional<Account> createAccount(Account account) {
        return accountDao.createAccount(account);
    }
    public static AccountService getInstance() {
        return INSTANCE;
    }
}
