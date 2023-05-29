package by.boitman.database.dao;

import by.boitman.database.dto.AccountDto;
import by.boitman.database.entity.AccountEntity;
import by.boitman.database.entity.enam.Gender;
import by.boitman.database.hibernate.HibernateFactory;
import lombok.Cleanup;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountDaoTest {

    private static final AccountDao accountDao = AccountDao.getInstance();
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
        String[] expected = List.of("Petr", "Nikolai")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(2)
    void whenFindAllDtosInvoked_ThenAllTheBooksDtosAreReturned() {
        @Cleanup Session session = sessionFactory.getSession();
        AccountDto[] actual = accountDao.findAllDtos(session).toArray(AccountDto[]::new);
        AccountDto[] expected = accountDao.findAll(session)
                .stream()
                .map(account -> new AccountDto(account.getNumberAccount(),
                        account.getUsers().size() > 0
                                ? account.getUsers().get(0).getSurname()
                                : null))
                .toArray(AccountDto[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(3)
    void whenFindAllByGenderInvoked_ThenAllTheAccountsOfGenderAreReturned() {
        @Cleanup Session session = sessionFactory.getSession();
        String[] actual = accountDao.findAllByGender(session, Gender.MALE)
                .stream()
                .map(AccountEntity::getOwnerNameAccount)
                .toArray(String[]::new);
        String[] expected = List.of("Petr", "Nikolai")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(4)
    void whenFindAllByUserInvoked_ThenAllTheAccountsOfUserAreReturned() {
        @Cleanup Session session = sessionFactory.getSession();
        String[] actual = accountDao.findAllByUser(session, "Petr")
                .stream()
                .map(AccountEntity::getNumberAccount)
                .toArray(String[]::new);
        String[] expected = List.of("123")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

//    @Test
//    @Order(5)
//    void whenFindAllByFilterContainsOnlyAuthorInvoked_ThenAllTheFilteredByAuthorBooksAreReturned() {
//        @Cleanup Session session = sessionFactory.getSession();
//        BookFilter filter = BookFilter.builder()
//                .authorName("Leo Tolstoi")
//                .build();
//        String[] actual = bookDao.findByFilter(session, filter)
//                .stream()
//                .map(BookEntity::getTitle)
//                .toArray(String[]::new);
//        String[] expected = List.of("Anna Karenina", "War and Peace")
//                .toArray(String[]::new);
//        assertArrayEquals(expected, actual);
//    }
//
//    @Test
//    @Order(6)
//    void whenFindAllByFilterContainsAuthorAndPagesInvoked_ThenAllTheFilteredByAuthorAndPagesBooksAreReturned() {
//        @Cleanup Session session = sessionFactory.getSession();
//        BookFilter filter = BookFilter.builder()
//                .authorName("Leo Tolstoi")
//                .pagesAmount(1000)
//                .build();
//        String[] actual = bookDao.findByFilter(session, filter)
//                .stream()
//                .map(BookEntity::getTitle)
//                .toArray(String[]::new);
//        String[] expected = List.of("Anna Karenina")
//                .toArray(String[]::new);
//        assertArrayEquals(expected, actual);
//    }
//
//    @Test
//    @Order(7)
//    void whenFindById_ThenAllTheFilteredReturnsValidBook() {
//        @Cleanup Session session = sessionFactory.getSession();
//        Optional<BookEntity> actual = bookDao.findById(session, 1L);
//        assertTrue(actual.isPresent());
//        assertEquals("Anna Karenina", actual.get().getTitle());
//    }
//
//    @Test
//    @Order(8)
//    void whenCreatedInvokedWithBook_ThenBookIsSaved() {
//        BookEntity testBook = BookEntity.builder()
//                .title("Test")
//                .genre(CLASSIC)
//                .pages(200)
//                .build();
//
//        @Cleanup Session session = sessionFactory.getSession();
//        var transaction = session.beginTransaction();
//        Optional<BookEntity> bookEntity = bookDao.create(session, testBook);
//        transaction.commit();
//
//        List<String> allTitles = bookDao.findAll(session).stream()
//                .map(BookEntity::getTitle)
//                .toList();
//        assertTrue(allTitles.contains(testBook.getTitle()));
//    }
}