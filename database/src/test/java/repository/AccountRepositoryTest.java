package repository;

import by.boitman.database.config.DatabaseConfig;
import by.boitman.database.repository.AccountRepository;
import by.boitman.database.repository.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DatabaseConfig.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SqlGroup({
        @Sql(value = "classpath:test-data.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "classpath:purge-data.sql", executionPhase = AFTER_TEST_METHOD)
})
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;


//    @Test
//    @Order(1)
//    void whenFindAllInvoked_ThenAllTheAccountsAreReturned() {
//        List<AccountEntity> actual = accountRepository.findAll();
//        List<String> expected = List.of("Ivan", "Petr");
//        assertEquals(expected.size(), actual.size());
//    }


//    @Test
//    @Order(2)
//    @Transactional
//    void whenFindAllByUserInvoked_ThenAllTheAccountsOfUserAreReturned() {
//        Optional<UserEntity> petr = userRepository.findByName("Petr");
//        List<AccountEntity> allByUsersContains = accountRepository.findAllByUsersContains(petr.get());
//
//        String[] actual = accountRepository.findAllByUsersContains(petr.get())
//                .stream()
//                .map(AccountEntity::getOwnerSurnameAccount)
//                .toArray(String[]::new);
//        String[] expected = List.of("Petrov")
//                .toArray(String[]::new);
//        assertArrayEquals(expected, actual);
//    }
//
//    @Test
//    @Order(3)
//    void whenFindAllByFilterContainsOnlyUserInvoked_ThenAllTheFilteredByUserAccountAreReturned() {
//
//        AccountFilter filter = AccountFilter.builder()
//                .userName("Petr")
//                .build();
//
//        Float[] actual = accountRepository
//                .findByFilter(filter)
//                .stream()
//                .map(AccountEntity::getAccountBalance)
//                .toArray(Float[]::new);
//        Float[] expected = List.of(1000.9f)
//                .toArray(Float[]::new);
//        assertArrayEquals(expected, actual);
//    }

//    @Test
//    @Order(4)
//    void whenFindAllByFilterContainsUserAndAccountBalanceAndInvoked_ThenAllTheFilteredByUserAndAccountBalanceAreReturned() {
//        AccountFilter filter = AccountFilter.builder()
//                .userName("Petr")
//                .accountBalance("1000.9")
//                .build();
//        Long[] actual = accountRepository.findByFilter(filter)
//                .stream()
//                .map(AccountEntity::getNumberAccount)
//                .toArray(Long[]::new);
//        Integer[] expected = List.of(123)
//                .toArray(Integer[]::new);
//        assertArrayEquals(expected, actual);
//    }

//    @Test
//    @Order(8)
//    void whenCreatedInvokedWithAccount_ThenAccountIsSaved() {
//        AccountEntity testAccount = AccountEntity.builder()
//                .numberAccount(9L)
//                .ownerNameAccount("Vasya")
//                .ownerSurnameAccount("Vasiliev")
//                .gender(Gender.valueOf("MALE"))
//                .accountBalance(9500F)
//                .build();
//
//
//        AccountEntity AccountEntity = AccountRepository.save(testAccount);
//
//        List<String> allOwnerNameAccount = accountRepository.findAll().stream()
//                .map(AccountEntity::getOwnerNameAccount)
//                .toList();
//        assertTrue(allOwnerNameAccount.contains(testAccount.getOwnerNameAccount()));
//    }

//    @Test
//    @Order(5)
//    void testFindAllByOwnerNameAccountIsLikeIgnoreCaseAndAccountBalanceLessThan() {
//        List<String> allOwnerNameAccount = accountRepository.findAllBy("Petr", 1000.9F).stream()
//                .map(AccountEntity::getOwnerNameAccount)
//                .toList();
//        assertTrue(allOwnerNameAccount.contains("Ivan"));
//    }

//    @Test
//    @Order(10)
//    void testFindAllByUser() {
//        List<String> allOwnerNameAccount = AccountRepository.findAllBy("Petr").stream()
//                .map(AccountEntity::getOwnerNameAccount)
//                .toList();
//        assertTrue(allOwnerNameAccount.containsAll(List.of("Petrov")));
//    }

//    @Test
//    @Order(6)
//    @Transactional
//    void testFindSetSurnameById() {
//        Optional<AccountEntity> numberAccount = accountRepository.findByNumberAccount(123L);
//        accountRepository.setNumberAccountById("Petr", numberAccount.get().getId());
//        assertTrue(accountRepository.findByNumberAccount(123L).isPresent());
//    }
}