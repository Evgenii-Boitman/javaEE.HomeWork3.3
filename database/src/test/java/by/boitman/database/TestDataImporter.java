package by.boitman.database;

import by.boitman.database.entity.AccountEntity;
import by.boitman.database.entity.CardEntity;
import by.boitman.database.entity.UserEntity;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;

import static by.boitman.database.entity.enam.Gender.FEMALE;
import static by.boitman.database.entity.enam.Gender.MALE;
import static by.boitman.database.entity.enam.Role.USER;

@UtilityClass
public class TestDataImporter {

    public void importTestData(Session session) {
        UserEntity petrov = UserEntity.builder()
                .name("Petr")
                .surname("Petrov")
                .email("petrpetrov@gmail.com")
                .password("petrpetrov")
                .gender(MALE)
                .role(USER)
                .contact("+375441000003")
                .build();

        session.persist(petrov);

        UserEntity nikolaev = UserEntity.builder()
                .name("Nikolai")
                .surname("Nikolaev")
                .email("nikolainikolaev@gmail.com")
                .password("nikolainikolaev")
                .gender(MALE)
                .role(USER)
                .contact("+375441000004")
                .build();

        session.persist(petrov);

        var accountPetrov = AccountEntity.builder()
                .ownerNameAccount("Petr")
                .ownerSurnameAccount("Petrov")
                .gender(MALE)
                .numberAccount(1L)
                .accountBalance(1000.5f)
                .build();
        var accountNikolaev = AccountEntity.builder()
                .ownerNameAccount("Nikolai")
                .ownerSurnameAccount("Nikolaev")
                .gender(FEMALE)
                .numberAccount(2L)
                .accountBalance(90.5f)
                .build();

        var cardPetrov = CardEntity.builder()
                .cardNumber(3L)
                .ownerName("Ivan")
                .ownerSurname("Petrov")
                .balance(199.9f)
                .build();
        var cardNikolaev = CardEntity.builder()
                .cardNumber(4L)
                .ownerName("Vasya")
                .ownerSurname("Nikolaev")
                .balance(299.9f)
                .build();

        accountPetrov.setUsers(petrov);
        accountNikolaev.setUsers(nikolaev);
        cardPetrov.setAccounts(accountPetrov);
        cardNikolaev.setAccounts(accountNikolaev);

        session.persist(accountNikolaev);
        session.persist(accountPetrov);
        session.persist(cardPetrov);
        session.persist(cardNikolaev);

    }
}
