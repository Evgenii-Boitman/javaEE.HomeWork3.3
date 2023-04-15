package by.boitman.database;

import by.boitman.database.entity.Card;
import by.boitman.database.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@Getter
@NoArgsConstructor(access = PRIVATE)
public class DummyDatabase {
    public static final double BALANCE = 100.5;
    public static final double BALANCE_1 = 1542.5;

    public static final LocalDate DATE = LocalDate.of(2023, 2, 15);

    public static final LocalDate DATE_1 = LocalDate.of(2022, 5, 25);
    public static final DummyDatabase INSTANCE = new DummyDatabase();
    private final Map<Long, Card> cards = new HashMap<>() {{
        put(1L, Card.builder()
                .id(1L)
                .ownerName("Петр")
                .ownerSurname("Петров")
                .dateCard(DATE)
                .cardNumber(1L)
                .balance(BALANCE)
                .build());
        put(2L, Card.builder()
                .id(2L)
                .ownerName("Иван")
                .ownerSurname("Иванов")
                .dateCard(DATE_1)
                .cardNumber(2L)
                .balance(BALANCE_1)
                .build());
    }};
    private final Map<Long, User> users = new HashMap<>() {{
        put(1L, User.builder()
                .id(1L)
                .name("Bob")
                .surname("Smith")
                .email("bobsmith@gmail.com")
                .password("bobsmith1985")
                .gender("MALE")
                .build());
        put(2L, User.builder()
                .id(2L)
                .name("Ann")
                .surname("Black")
                .email("annblack@gmail.com")
                .password("blackann")
                .gender("FEMALE")
                .build());
    }};
    public static DummyDatabase getInstance() {
        return INSTANCE;
    }
}
