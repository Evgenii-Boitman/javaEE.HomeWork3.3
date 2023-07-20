package by.boitman.service;

import by.boitman.database.dto.AccountFilter;
import by.boitman.database.entity.AccountEntity;
import by.boitman.database.entity.UserEntity;
import by.boitman.database.entity.enam.Gender;
import by.boitman.database.entity.enam.Role;
import by.boitman.database.repository.AccountRepository;;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public List<AccountEntity> getAll() {
        return accountRepository.findAll();
    }

    public List<AccountEntity> getFindByFilter(AccountFilter filter) {
        return accountRepository.findByFilter(filter);
    }

    public AccountEntity getById(Long id) {
        return accountRepository.findById(id)
                .orElse(AccountEntity.builder()
                        .ownerNameAccount("Указанный пользователь не найден!")
                        .build());
    }
    public AccountEntity save(AccountEntity account) {
        account.setGender(Gender.MALE);
        return accountRepository.save(account);
    }
    public AccountEntity create(AccountEntity card) {
        return accountRepository.save(card);
    }
//    public List<AccountEntity> getAll() {
//        List<AccountEntity> accounts;
//        try (Session session = hibernateFactory.getSession()) {
//            Transaction transaction = session.beginTransaction();
//            accounts = ACCOUNT_DAO.findAll(session);
//            transaction.commit();
//        }
//        return accounts;
//    }
//
//    public List<AccountEntity> getFindByFilter(AccountFilter filter) {
//        List<AccountEntity> accounts;
//        try (Session session = hibernateFactory.getSession()) {
//            Transaction transaction = session.beginTransaction();
//            accounts = ACCOUNT_DAO.findByFilter(session, filter);
//            transaction.commit();
//        }
//        return accounts;
//    }
//
//    public AccountEntity getById(Long id) {
//        AccountEntity account;
//        try (Session session = hibernateFactory.getSession()) {
//            Transaction transaction = session.beginTransaction();
//            account = ACCOUNT_DAO.findById(session, id)
//                    .orElse(AccountEntity.builder()
//                            .ownerNameAccount("Указанный пользователь не найден!")
//                            .build());
//            transaction.commit();
//        }
//        return account;
//    }
//
//    public Optional<AccountEntity> create(AccountEntity account) {
//        Optional<AccountEntity> newAccount;
//        try (Session session = hibernateFactory.getSession()) {
//            Transaction transaction = session.beginTransaction();
//            newAccount = ACCOUNT_DAO.create(session, account);
//            transaction.commit();
//        }
//        return newAccount;
//    }
//    public static AccountService getInstance() {
//        return INSTANCE;
//    }
}
