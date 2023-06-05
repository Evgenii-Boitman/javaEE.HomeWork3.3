package by.boitman.database;

import by.boitman.database.entity.AccountEntity;
import by.boitman.database.entity.UserEntity;
import by.boitman.database.entity.enam.Gender;
import by.boitman.database.entity.enam.Role;
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


        accountPetrov.addUser(petrov);
        accountNikolaev.addUser(nikolaev);


        session.persist(accountNikolaev);
        session.persist(accountPetrov);

    }
}
