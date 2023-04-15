package by.boitman.database;

import by.boitman.database.dao.CardDao;
import by.boitman.database.entity.Card;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CardService {
    private static final CardService INSTANCE = new CardService();
    private final CardDao cardDao = CardDao.getInstance();

    public List<Card> getAll() {
        return cardDao.getAll();
    }

    public Card getById(Long id) {
        return cardDao.getById(id)
                .orElse(Card.builder()
                        .ownerName("Данный пользователь отсутствует в базе")
                        .build());
    }

    public static CardService getInstance() {
        return INSTANCE;
    }
}
