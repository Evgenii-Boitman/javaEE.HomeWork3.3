package by.boitman.service;

import by.boitman.database.dto.LoginDto;
import by.boitman.database.dto.RegistrationDto;
import by.boitman.database.entity.UserEntity;
import by.boitman.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static by.boitman.database.entity.enam.Role.USER;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    public Optional<UserEntity> findByEmailAndPass(LoginDto login) {
        return userRepository.findByEmailAndPassword(login.email(), login.password());
    }
    public Optional<UserEntity> getBy(LoginDto login) {
        return userRepository.findByEmailAndPassword(login.email(), login.password());
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    public UserEntity save(UserEntity user) {
        user.setRole(USER);
        return userRepository.save(user);
    }

    public Optional<UserEntity> createUser(RegistrationDto registration) {
        return Optional.of(userRepository.save(UserEntity.builder()
                .name(registration.name())
                .surname(registration.surname())
                .email(registration.email())
                .password(passwordEncoder.encode(registration.password()))
                .gender(registration.gender())
                .role(USER)
                .contact(registration.contact())
                .build()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(userEntity -> User.builder()
                        .username(userEntity.getEmail())
                        .password(userEntity.getPassword())
                        .authorities(userEntity.getRole())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> update(UserEntity user) {
        return update(user);
    }

    public boolean delete(Long id) {
        Optional<UserEntity> removedUser = userRepository.findById(id);
        userRepository.delete(removedUser.get());
        return true;
    }
}

