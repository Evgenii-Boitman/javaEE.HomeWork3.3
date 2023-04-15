package by.boitman.database;

import by.boitman.database.dao.CreditCardDao;
import by.boitman.database.entity.CreditCard;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreditCardService {
    private static final CreditCardService INSTANCE = new CreditCardService();
    private final CreditCardDao creditCardDao = CreditCardDao.getInstance();

    public List<CreditCard> getAll() {
        return creditCardDao.getAll();
    }

    public CreditCard getById(Long id) {
        return creditCardDao.getById(id)
                .orElse(CreditCard.builder()
                        .ownerName("Данный пользователь отсутствует в базе")
                        .build());
    }

    public static CreditCardService getInstance() {
        return INSTANCE;
    }
}
