package by.boitman.service;

import by.boitman.database.dto.CardFilter;
import by.boitman.database.entity.CardEntity;
import by.boitman.database.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public List<CardEntity> getAll() {
        return cardRepository.findAll();
    }

    public List<CardEntity> getFindByFilter(CardFilter filter) {
        return cardRepository.findByFilter(filter);
    }

    public CardEntity getById(Long id) {
        return cardRepository.findById(id)
                .orElse(CardEntity.builder()
                        .ownerName("Указанный пользователь не найден!")
                        .build());
    }

    public CardEntity create(CardEntity card) {
        return cardRepository.save(card);
    }
//    private static final CardService INSTANCE = new CardService();
//    private final CardDao cardDao = CardDao.getInstance();
//
//    private final HibernateFactory hibernateFactory = HibernateFactory.getInstance();
//
//    public List<CardEntity> getAll() {
//        List<CardEntity> cards;
//        try (Session session = hibernateFactory.getSession()) {
//            Transaction transaction = session.beginTransaction();
//            cards = cardDao.findAll(session);
//            transaction.commit();
//        }
//        return cards;
//    }
//
//    public List<CardEntity> getFindByFilter(CardFilter filter) {
//        List<CardEntity> cards;
//        try (Session session = hibernateFactory.getSession()) {
//            Transaction transaction = session.beginTransaction();
//            cards = cardDao.findByFilter(session, filter);
//            transaction.commit();
//        }
//        return cards;
//    }
//
//    public CardEntity getById(Long id) {
//        CardEntity card;
//        try (Session session = hibernateFactory.getSession()) {
//            Transaction transaction = session.beginTransaction();
//            card = cardDao.findById(session, id)
//                    .orElse(CardEntity.builder()
//                            .ownerName("Указанный пользователь не найден!")
//                            .build());
//            transaction.commit();
//        }
//        return card;
//    }
//
//    public Optional<CardEntity> create(CardEntity card) {
//        Optional<CardEntity> newCard;
//        try (Session session = hibernateFactory.getSession()) {
//            Transaction transaction = session.beginTransaction();
//            newCard = cardDao.create(session, card);
//            transaction.commit();
//        }
//        return newCard;
//    }
//
//    public static CardService getInstance() {
//        return INSTANCE;
//    }
}
