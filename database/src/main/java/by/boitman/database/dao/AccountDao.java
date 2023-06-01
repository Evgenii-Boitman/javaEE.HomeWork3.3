package by.boitman.database.dao;


import by.boitman.database.dto.AccountDto;
import by.boitman.database.entity.AccountEntity;
import by.boitman.database.dto.AccountFilter;

import by.boitman.database.entity.AccountEntity_;
import by.boitman.database.entity.UserEntity_;
import by.boitman.database.entity.enam.Gender;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaJoin;
import org.hibernate.query.criteria.JpaRoot;
import java.util.ArrayList;
import java.util.List;



public final class AccountDao extends Dao<Long, AccountEntity> {
    private static final AccountDao INSTANCE = new AccountDao();
    private AccountDao() {
        super(AccountEntity.class);
    }


    public List<AccountDto> findAllDtos(Session session) {
        JpaCriteriaQuery<AccountDto> query = session.getCriteriaBuilder().createQuery(AccountDto.class);
        JpaRoot<AccountEntity> accountRoot = query.from(AccountEntity.class);
        JpaJoin<Object, Object> users = accountRoot.join(AccountEntity_.USERS, JoinType.LEFT);
        query.multiselect(accountRoot.get(AccountEntity_.NUMBER_ACCOUNT), users.get(UserEntity_.NAME));
        return session.createQuery(query).list();
    }

    public List<AccountEntity> findAllByGender(Session session, Gender gender) {
        HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
        JpaCriteriaQuery<AccountEntity> query = cb.createQuery(AccountEntity.class);
        JpaRoot<AccountEntity> accountRoot = query.from(AccountEntity.class);
        query.select(accountRoot);
        query.where(cb.equal(accountRoot.get(AccountEntity_.GENDER), gender));
        return session.createQuery(query).list();
    }

    public List<AccountEntity> findAllByUser(Session session, String name) {
        HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
        JpaCriteriaQuery<AccountEntity> query = cb.createQuery(AccountEntity.class);
        JpaRoot<AccountEntity> accountRoot = query.from(AccountEntity.class);
        query.select(accountRoot);
        JpaJoin<Object, Object> users = accountRoot.join(AccountEntity_.USERS);
        query.where(cb.equal(users.get(UserEntity_.NAME), name));
        return session.createQuery(query).list();
    }
    public List<AccountEntity> findByFilter(Session session, AccountFilter filter) {
        HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
        JpaCriteriaQuery<AccountEntity> query = cb.createQuery(AccountEntity.class);
        JpaRoot<AccountEntity> accountRoot = query.from(AccountEntity.class);
        query.select(accountRoot);
        JpaJoin<Object, Object> users = accountRoot.join(AccountEntity_.USERS);
        query.where(collectPredicates(filter, cb, accountRoot, users).toArray(Predicate[]::new));

        return session.createQuery(query)
                .setMaxResults(filter.getLimit())
                .setFirstResult(filter.getOffset())
                .list();
    }

    private static List<Predicate> collectPredicates(AccountFilter filter, HibernateCriteriaBuilder cb, JpaRoot<AccountEntity> cardRoot, JpaJoin<Object, Object> users) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getAccountBalance() != null) {
            predicates.add(cb.le(cardRoot.get("accountBalance"), filter.getAccountBalance()));
        }
        return predicates;
    }

    public static AccountDao getInstance() {
        return INSTANCE;
    }
}
