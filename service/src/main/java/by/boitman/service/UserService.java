package by.boitman.service;

import by.boitman.database.entity.UserEntity;
import by.boitman.database.entity.enam.Role;
import by.boitman.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AccountService accountService;
    private final CardService cardService;

    public Optional<UserEntity> getBy(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    public UserEntity save(UserEntity user) {
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

//    private static final UserService INSTANCE = new UserService();
//    private final UserDao userDao = UserDao.getInstance();
//    private final HibernateFactory hibernateFactory = HibernateFactory.getInstance();
//
//    public Optional<UserEntity> getBy(String email, String password) {
//        Optional<UserEntity> user;
//        try (Session session = hibernateFactory.getSession()) {
//            Transaction transaction = session.beginTransaction();
//            user = userDao.getByEmailAndPass(session, email, password);
//            transaction.commit();
//        }
//        return user;
//    }
//
//    public Optional<UserEntity> save(UserEntity user) {
//        Optional<UserEntity> newUser;
//        try (Session session = hibernateFactory.getSession()) {
//            Transaction transaction = session.beginTransaction();
//            newUser = userDao.create(session, user);
//            transaction.commit();
//        }
//        return newUser;
//    }
//
//
//    public static UserService getInstance() {
//        return INSTANCE;
//    }
}

