package service;

import by.boitman.database.dao.CardDao;
import by.boitman.database.dto.CardFilter;
import by.boitman.database.entity.CardEntity;
import by.boitman.database.hibernate.HibernateFactory;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CardService {
    private static final CardService INSTANCE = new CardService();
    private final CardDao cardDao = CardDao.getInstance();

    private final HibernateFactory hibernateFactory = HibernateFactory.getInstance();

    public List<CardEntity> getAll() {
        List<CardEntity> cards;
        try (Session session = hibernateFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            cards = cardDao.findAll(session);
            transaction.commit();
        }
        return cards;
    }

    public List<CardEntity> getFindByFilter(CardFilter filter) {
        List<CardEntity> cards;
        try (Session session = hibernateFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            cards = cardDao.findByFilter(session, filter);
            transaction.commit();
        }
        return cards;
    }

    public CardEntity getById(Long id) {
        CardEntity card;
        try (Session session = hibernateFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            card = cardDao.findById(session, id)
                    .orElse(CardEntity.builder()
                            .ownerName("Указанный пользователь не найден!")
                            .build());
            transaction.commit();
        }
        return card;
    }

    public Optional<CardEntity> create(CardEntity card) {
        Optional<CardEntity> newCard;
        try (Session session = hibernateFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            newCard = cardDao.create(session, card);
            transaction.commit();
        }
        return newCard;
    }

    public static CardService getInstance() {
        return INSTANCE;
    }
}
