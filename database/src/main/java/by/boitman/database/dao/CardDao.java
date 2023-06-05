package by.boitman.database.dao;


import by.boitman.database.dto.CardDto;
import by.boitman.database.dto.CardFilter;
import by.boitman.database.entity.CardEntity;

import by.boitman.database.entity.CardEntity_;
import by.boitman.database.entity.UserEntity_;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaJoin;
import org.hibernate.query.criteria.JpaRoot;

import java.util.ArrayList;
import java.util.List;


public final class CardDao extends Dao<Long, CardEntity> {
    private static final CardDao INSTANCE = new CardDao();

    private CardDao() {
        super(CardEntity.class);
    }
    public List<CardDto> findAllDtos(Session session) {
        JpaCriteriaQuery<CardDto> query = session.getCriteriaBuilder().createQuery(CardDto.class);
        JpaRoot<CardEntity> cardRoot = query.from(CardEntity.class);
        JpaJoin<Object, Object> users = cardRoot.join(CardEntity_.USERS, JoinType.LEFT);
        query.multiselect(cardRoot.get(CardEntity_.CARD_NUMBER), users.get(UserEntity_.NAME));
        return session.createQuery(query).list();
    }

    public List<CardEntity> findAllByUser(Session session, String name) {
        HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
        JpaCriteriaQuery<CardEntity> query = cb.createQuery(CardEntity.class);
        JpaRoot<CardEntity> cardRoot = query.from(CardEntity.class);
        query.select(cardRoot);
        JpaJoin<Object, Object> users = cardRoot.join(CardEntity_.USERS);
        query.where(cb.equal(users.get(UserEntity_.NAME), name));
        return session.createQuery(query).list();
    }
    public List<CardEntity> findByFilter(Session session, CardFilter filter) {
        HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
        JpaCriteriaQuery<CardEntity> query = cb.createQuery(CardEntity.class);
        JpaRoot<CardEntity> cardRoot = query.from(CardEntity.class);
        query.select(cardRoot);
        JpaJoin<Object, Object> users = cardRoot.join(CardEntity_.USERS);
        query.where(collectPredicates(filter, cb, cardRoot, users).toArray(Predicate[]::new));

        return session.createQuery(query)
                .setMaxResults(filter.getLimit())
                .setFirstResult(filter.getOffset())
                .list();
    }

    private static List<Predicate> collectPredicates(CardFilter filter, HibernateCriteriaBuilder cb, JpaRoot<CardEntity> cardRoot, JpaJoin<Object, Object> users) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getBalance() != null) {
            predicates.add(cb.le(cardRoot.get(CardEntity_.BALANCE), filter.getBalance()));
        }
        if (filter.getOwnerName() != null) {
            predicates.add(cb.equal(cardRoot.get(CardEntity_.OWNER_NAME), filter.getOwnerName()));
        }
        return predicates;
    }

    public static CardDao getInstance() {
        return INSTANCE;
    }
}

