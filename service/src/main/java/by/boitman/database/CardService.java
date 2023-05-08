package by.boitman.database;

import by.boitman.database.dao.CardDao;
import by.boitman.database.dto.CardFilter;
import by.boitman.database.entity.Card;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CardService {
    private static final CardService INSTANCE = new CardService();
    private final CardDao cardDao = CardDao.getInstance();

    public List<Card> getAll() {
        return cardDao.findAll();
    }
    public List<Card> getFindByFilter(CardFilter filter) {
        return cardDao.findByFilter(filter);
    }

    public Card getById(Long id) {
        return cardDao.findById(id)
                .orElse(Card.builder()
                        .ownerName("Данный пользователь отсутствует в базе")
                        .build());
    }

    public Optional<Card> create(Card card) {
        return cardDao.create(card);
    }
    public static CardService getInstance() {
        return INSTANCE;
    }
}
