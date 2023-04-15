package by.boitman.database.dao;

import by.boitman.database.DummyDatabase;
import by.boitman.database.entity.CreditCard;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreditCardDao {
    private static final CreditCardDao INSTANCE = new CreditCardDao();
    private final DummyDatabase db = DummyDatabase.getInstance();

    public List<CreditCard> getAll() {
        return new ArrayList<>(db.getCreditCards().values());
    }

    public Optional<CreditCard> getById(Long id) {
        return Optional.ofNullable(db.getCreditCards().get(id));
    }

    public CreditCard create(CreditCard creditCard) {
        return db.getCreditCards().put(creditCard.getId(), creditCard);
    }

    public CreditCard delete(Long id) {
        return Optional.ofNullable(db.getCreditCards().remove(id))
                .orElseThrow(RuntimeException::new);
    }

    public static CreditCardDao getInstance() {
        return INSTANCE;
    }
}

