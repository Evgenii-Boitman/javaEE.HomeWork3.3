package by.boitman.database.repository;

import by.boitman.database.dto.CardFilter;
import by.boitman.database.entity.CardEntity;
import by.boitman.database.entity.CardEntity_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CardRepositoryExtensionImpl implements CardRepositoryExtension {

    @PersistenceContext
    private final EntityManager entityManager;

    public List<CardEntity> findByFilter(CardFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CardEntity> query = cb.createQuery(CardEntity.class);
        Root<CardEntity> cardRoot = query.from(CardEntity.class);
        query.select(cardRoot);
        Join<Object, Object> accounts = cardRoot.join(CardEntity_.ACCOUNTS);
        query.where(collectPredicates(filter, cb, cardRoot, accounts).toArray(Predicate[]::new));
        return entityManager.createQuery(query)
                .setMaxResults(filter.getLimit())
                .setFirstResult(filter.getOffset())
                .getResultList();
    }
    private static List<Predicate> collectPredicates(CardFilter filter, CriteriaBuilder cb, Root<CardEntity> cardRoot, Join<Object, Object> accounts) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getBalance() != null) {
            predicates.add(cb.equal(cardRoot.get(CardEntity_.BALANCE), filter.getBalance()));
        }
        if (filter.getOwnerName() != null) {
            predicates.add(cb.equal(cardRoot.get(CardEntity_.OWNER_NAME), filter.getOwnerName()));
        }
        return predicates;
    }
}
