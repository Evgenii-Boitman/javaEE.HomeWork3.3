package by.boitman.database.repository;

import by.boitman.database.dto.AccountFilter;
import by.boitman.database.entity.AccountEntity;
import by.boitman.database.entity.AccountEntity_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryExtensionImpl implements AccountRepositoryExtension {

    @PersistenceContext
    private final EntityManager entityManager;

    public List<AccountEntity> findByFilter(AccountFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AccountEntity> query = cb.createQuery(AccountEntity.class);
        Root<AccountEntity> accountRoot = query.from(AccountEntity.class);
        query.select(accountRoot);
        Join<Object, Object> users = accountRoot.join(AccountEntity_.USERS);
        query.where(collectPredicates(filter, cb, accountRoot, users).toArray(Predicate[]::new));
        return entityManager.createQuery(query)
                .setMaxResults(filter.getLimit())
                .setFirstResult(filter.getOffset())
                .getResultList();
    }

    private static List<Predicate> collectPredicates(AccountFilter filter, CriteriaBuilder cb, Root<AccountEntity> accountRoot, Join<Object, Object> users) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getAccountBalance() != null) {
            predicates.add(cb.le(accountRoot.get(AccountEntity_.ACCOUNT_BALANCE), filter.getAccountBalance()));
        }
        if (filter.getUserName() != null) {
            predicates.add(cb.equal(accountRoot.get(AccountEntity_.OWNER_NAME_ACCOUNT), filter.getUserName()));
        }
        return predicates;
    }
}
