package by.boitman.database.repository;

import by.boitman.database.config.DatabaseConfig;
import by.boitman.database.dto.AccountFilter;
import by.boitman.database.entity.AccountEntity;

import by.boitman.database.entity.UserEntity;
import by.boitman.database.entity.enam.Gender;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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


    @Test
    @Order(1)
    void whenFindAllInvoked_ThenAllTheAccountsAreReturned() {
        String[] actual = accountRepository.findAll()
                .stream()
                .map(AccountEntity::getOwnerNameAccount)
                .toArray(String[]::new);
        String[] expected = List.of("Petr")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }


//    @Test
//    @Order(4)
//    @Transactional
//    void whenFindAllByUserInvoked_ThenAllTheAccountsOfUserAreReturned() {
//        Optional<UserEntity> petr = userRepository.findByName("Petr");
//        List<AccountEntity> allByUsersContains = accountRepository.findAllByUsersContains(petr.get());
//
//        String[] actual = accountRepository.findAllByUsersContains(petr.get())
//                .stream()
//                .map(AccountEntity::getOwnerNameAccount)
//                .toArray(String[]::new);
//        String[] expected = List.of("Petr")
//                .toArray(String[]::new);
//        assertArrayEquals(expected, actual);
//    }

//    @Test
//    @Order(5)
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
//        Float[] expected = List.of(100.5)
//                .toArray(Float[]::new);
//        assertArrayEquals(expected, actual);
//    }

//    @Test
//    @Order(6)
//    void whenFindAllByFilterContainsUserAndAccountBalanceAndInvoked_ThenAllTheFilteredByUserAndAccountBalanceAreReturned() {
//        AccountFilter filter = AccountFilter.builder()
//                .userName("Petr")
//                .accountBalance("100.5")
//                .build();
//        Long[] actual = accountRepository.findByFilter(filter)
//                .stream()
//                .map(AccountEntity::getNumberAccount)
//                .toArray(Long[]::new);
//        Long[] expected = List.of(1)
//                .toArray(Long[]::new);
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
//    @Order(9)
//    void testFindAllByOwnerNameAccountIsLikeIgnoreCaseAndAccountBalanceLessThan() {
//        List<String> allOwnerNameAccount = accountRepository.findAllBy("ada", 800F).stream()
//                .map(AccountEntity::getOwnerNameAccount)
//                .toList();
//        assertTrue(allOwnerNameAccount.contains("Petr"));
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
//    @Order(10)
//    @Transactional
//    void testFindSetTitleById() {
//        Optional<AccountEntity> numberAccount = accountRepository.findByNumberAccount(1L);
//        accountRepository.setNumberAccountById("Petr", numberAccount.get().getId());
//        assertTrue(accountRepository.findByNumberAccount(1L).isPresent());
//    }
}