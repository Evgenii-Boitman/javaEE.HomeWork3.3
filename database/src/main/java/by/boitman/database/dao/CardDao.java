package by.boitman.database.dao;

import by.boitman.database.DummyDatabase;
import by.boitman.database.entity.Card;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CardDao {
    private static final CardDao INSTANCE = new CardDao();
    private final DummyDatabase db = DummyDatabase.getInstance();

    public List<Card> getAll() {
        return new ArrayList<>(db.getCards().values());
    }

    public Optional<Card> getById(Long id) {
        return Optional.ofNullable(db.getCards().get(id));
    }

    public Card create(Card card) {
        return db.getCards().put(card.getId(), card);
    }

    public Card delete(Long id) {
        return Optional.ofNullable(db.getCards().remove(id))
                .orElseThrow(RuntimeException::new);
    }

    public static CardDao getInstance() {
        return INSTANCE;
    }
}

