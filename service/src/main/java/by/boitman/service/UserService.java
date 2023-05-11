package by.boitman.service;

import by.boitman.service.dao.UserDao;
import by.boitman.service.entity.User;
import lombok.NoArgsConstructor;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class UserService {
    private static final UserService INSTANCE = new UserService();
    private final UserDao userDao = UserDao.getInstance();

    public Optional<User> getBy(String email, String password) {
        return userDao.getByEmailAndPass(email, password);
    }

    public Optional<User> save(User user) {
        return userDao.createUser(user);
    }


    public static UserService getInstance() {
        return INSTANCE;
    }
}

