package by.boitman.database.dao;

import by.boitman.database.TestDataImporter;
import by.boitman.database.dto.AccountDto;
import by.boitman.database.dto.AccountFilter;
import by.boitman.database.entity.AccountEntity;
import by.boitman.database.entity.CardEntity;
import by.boitman.database.hibernate.HibernateFactory;
import lombok.Cleanup;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;
import java.util.Optional;

import static by.boitman.database.entity.enam.Gender.FEMALE;
import static by.boitman.database.entity.enam.Gender.MALE;
import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountDaoTest {

    private static final AccountDao accountDao = AccountDao.getInstance();
    private static final CardDao cardDao = CardDao.getInstance();
    private static final HibernateFactory sessionFactory = HibernateFactory.getInstance();

    @BeforeAll
    static void beforeAll() {
        try (var session = sessionFactory.getSession()) {
            var transaction = session.beginTransaction();
            TestDataImporter.importTestData(session);
            transaction.commit();
        }
    }

    @Test
    @Order(1)
    void whenFindAllInvoked_ThenAllTheOwnersAreReturned() {
        @Cleanup Session session = sessionFactory.getSession();
        String[] actual = accountDao.findAll(session)
                .stream()
                .map(AccountEntity::getOwnerNameAccount)
                .toArray(String[]::new);
        String[] expected = List.of("Nikolai", "Petr")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(2)
    void whenFindAllDtosInvoked_ThenAllTheAccountsDtosAreReturned() {
        @Cleanup Session session = sessionFactory.getSession();
        AccountDto[] actual = accountDao.findAllDtos(session).toArray(AccountDto[]::new);
        AccountDto[] expected = accountDao.findAll(session)
                .stream()
                .map(account -> new AccountDto(account.getNumberAccount(),
                        account.getUsers().size() > 0
                                ? account.getUsers().get(0).getName()
                                : null))
                .toArray(AccountDto[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(3)
    void whenFindAllByGenderInvoked_ThenAllTheAccountsOfGenderAreReturned() {
        @Cleanup Session session = sessionFactory.getSession();
        String[] actual = accountDao.findAllByGender(session, MALE)
                .stream()
                .map(AccountEntity::getOwnerSurnameAccount)
                .toArray(String[]::new);
        String[] expected = List.of("Petrov")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(4)
    void whenFindAllByUserInvoked_ThenAllTheAccountsOfUserAreReturned() {
        @Cleanup Session session = sessionFactory.getSession();
        String[] actual = accountDao.findAllByUser(session, "Petr")
                .stream()
                .map(AccountEntity::getOwnerSurnameAccount)
                .toArray(String[]::new);
        String[] expected = List.of("Petrov")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(5)
    void whenFindAllByFilterContainsOnlyUserInvoked_ThenAllTheFilteredByUserAccountAreReturned() {
        @Cleanup Session session = sessionFactory.getSession();
        AccountFilter filter = AccountFilter.builder()
                .userName("Petr")
                .build();
        Float[] actual = accountDao.findByFilter(session, filter)
                .stream()
                .map(AccountEntity::getAccountBalance)
                .toArray(Float[]::new);
        Float[] expected = List.of(1000.5f)
                .toArray(Float[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(6)
    void whenFindAllByFilterContainsUserAndBalanceInvoked_ThenAllTheFilteredByUserAndBalanceAccountAreReturned() {
        @Cleanup Session session = sessionFactory.getSession();
        AccountFilter filter = AccountFilter.builder()
                .userName("Petr")
                .accountBalance(String.valueOf(1000.5))
                .build();
        String[] actual = accountDao.findByFilter(session, filter)
                .stream()
                .map(AccountEntity::getOwnerSurnameAccount)
                .toArray(String[]::new);
        String[] expected = List.of("Petrov")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(7)
    void whenFindById_ThenAllTheFilteredReturnsValidAccount() {
        @Cleanup Session session = sessionFactory.getSession();
        Optional<AccountEntity> actual = accountDao.findById(session, 2L);
        assertTrue(actual.isPresent());
        assertEquals("Petrov", actual.get().getOwnerSurnameAccount());
    }

    @Test
    @Order(8)
    void whenCreatedInvokedWithAccount_ThenAccountIsSaved() {
        AccountEntity testAccount = AccountEntity.builder()
                .ownerNameAccount("Test")
                .ownerSurnameAccount("Testov")
                .gender(FEMALE)
                .numberAccount(1L)
                .accountBalance(9999.9f)
                .build();
        CardEntity testCard = CardEntity.builder()
                .ownerName("Test")
                .ownerSurname("Testov")
                .cardNumber(1L)
                .balance(123213f)
                .build();

        @Cleanup Session session = sessionFactory.getSession();
        var transaction = session.beginTransaction();
        Optional<AccountEntity> accountEntity = accountDao.create(session, testAccount);
        Optional<CardEntity> cardEntity = cardDao.create(session, testCard);
        transaction.commit();

        List<String> allNames = accountDao.findAll(session).stream()
                .map(AccountEntity::getOwnerNameAccount)
                .toList();
        assertTrue(allNames.contains(testAccount.getOwnerNameAccount()));

        List<String> allNames2 = cardDao.findAll(session).stream()
                .map(CardEntity::getOwnerName)
                .toList();
        assertTrue(allNames2.contains(testCard.getOwnerName()));
    }
}