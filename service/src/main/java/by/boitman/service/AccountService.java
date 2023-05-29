package by.boitman.service;

import by.boitman.database.dao.AccountDao;
import by.boitman.database.dto.AccountFilter;
import by.boitman.database.entity.AccountEntity;
import by.boitman.database.hibernate.HibernateFactory;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class AccountService {
    private static final AccountService INSTANCE = new AccountService();
    private static final AccountDao ACCOUNT_DAO = AccountDao.getInstance();
    private final HibernateFactory hibernateFactory = HibernateFactory.getInstance();

    public List<AccountEntity> getAll() {
        List<AccountEntity> accounts;
        try (Session session = hibernateFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            accounts = ACCOUNT_DAO.findAll(session);
            transaction.commit();
        }
        return accounts;
    }

    public List<AccountEntity> getFindByFilter(AccountFilter filter) {
        List<AccountEntity> accounts;
        try (Session session = hibernateFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            accounts = ACCOUNT_DAO.findByFilter(session, filter);
            transaction.commit();
        }
        return accounts;
    }

    public AccountEntity getById(Long id) {
        AccountEntity account;
        try (Session session = hibernateFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            account = ACCOUNT_DAO.findById(session, id)
                    .orElse(AccountEntity.builder()
                            .ownerNameAccount("Указанный пользователь не найден!")
                            .build());
            transaction.commit();
        }
        return account;
    }

    public Optional<AccountEntity> create(AccountEntity account) {
        Optional<AccountEntity> newAccount;
        try (Session session = hibernateFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            newAccount = ACCOUNT_DAO.create(session, account);
            transaction.commit();
        }
        return newAccount;
    }
    public static AccountService getInstance() {
        return INSTANCE;
    }
}
