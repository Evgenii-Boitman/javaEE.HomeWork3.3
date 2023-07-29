package repository;

import by.boitman.database.config.DatabaseConfig;
import by.boitman.database.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DatabaseConfig.class})
@Sql("classpath:test-data.sql")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

//    @Test
//    void findById() {
//        Page<UserEntity> users = userRepository.findAll(Pageable
//                .ofSize(2)
//                .withPage(0));
//        users.forEach(System.out::println);
//    }
//
//    @Test
//    void findByEmailAndPassword() {
//        Optional<UserEntity> byEmailAndPassword = userRepository.findByEmailAndPassword("anna@anna.by", "anna@anna.by");
//        List<UserEntity> allByPasswordNotEmpty = userRepository.findAllByPasswordNotNull();
//        System.out.println();
//    }
}