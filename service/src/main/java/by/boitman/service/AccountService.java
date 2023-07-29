package by.boitman.service;

import by.boitman.database.dto.AccountCreationDto;
import by.boitman.database.dto.AccountFilter;
import by.boitman.database.dto.AccountReadDto;
import by.boitman.database.entity.AccountEntity;
import by.boitman.database.entity.UserEntity;
import by.boitman.database.entity.enam.Gender;
import by.boitman.database.repository.AccountRepository;
import by.boitman.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;



    public List<AccountReadDto> getAll() {
        return accountRepository.findAll()
                .stream()
                .map(this::toReadDto)
                .toList();
    }

    @Cacheable(value = "accounts", unless = "#result.size() > 100")
    public List<AccountEntity> getFindByFilter(AccountFilter filter) {
        return accountRepository.findByFilter(filter);
    }


    public AccountEntity findById(Long id) {
        return accountRepository.findById(id)
                .orElse(AccountEntity.builder().build());
    }
    @Cacheable("account")
    public Optional<AccountReadDto> getById(Long id) {
        return accountRepository.findById(id)
                .map(this::toReadDto);
    }

    public Long create(AccountCreationDto account) {
        AccountEntity newAccount = AccountEntity
                .builder()
                .ownerNameAccount(account.getOwnerNameAccount())
                .ownerSurnameAccount(account.getOwnerSurnameAccount())
                .gender(account.getGender())
                .numberAccount(account.getNumberAccount())
                .accountBalance(account.getAccountBalance())
                .build();
        return accountRepository.save(newAccount).getId();
    }

    @CachePut(value = "account", key = "#id")
    public Optional<AccountReadDto> update(Long id, AccountCreationDto update) {
        Optional<AccountEntity> existedAccount = accountRepository.findById(id);
        if (existedAccount.isPresent()) {
            AccountEntity account = existedAccount.get();
            account.setOwnerNameAccount(update.getOwnerNameAccount());
            account.setOwnerSurnameAccount(update.getOwnerSurnameAccount());
            account.setGender(update.getGender());
            account.setNumberAccount(update.getNumberAccount());
            account.setAccountBalance(update.getAccountBalance());
            return Optional.of(toReadDto(accountRepository.save(account)));
        }
        return Optional.empty();
    }

    @Caching(evict = {
            @CacheEvict(value = "account", key = "#id"),
            @CacheEvict(value = "accounts", allEntries = true)
    })
        public void delete(Long id) {
        accountRepository.findById(id)
                .ifPresent(accountRepository::delete);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    private AccountReadDto toReadDto(AccountEntity account) {
        return new AccountReadDto(account.getId(),
                account.getOwnerNameAccount(),
                account.getOwnerSurnameAccount(),
                account.getGender(),
                account.getNumberAccount(),
                account.getAccountBalance());
    }



    public Optional<AccountEntity> save(AccountCreationDto account) {
        return Optional.of(accountRepository.save(AccountEntity.builder()
                .ownerNameAccount(account.getOwnerNameAccount())
                .ownerSurnameAccount(account.getOwnerSurnameAccount())
                .gender(account.getGender())
                .numberAccount(account.getNumberAccount())
                .accountBalance(account.getAccountBalance())
                .users((UserEntity) account.getUsersIds())
                .build()));
    }






    public List<AccountEntity> getAllOwnerNameAccount() {
        return accountRepository.findAll();
    }

    public List<AccountEntity> getAllOwnerSurnameAccount() {
        return accountRepository.findAll();
    }

    public List<Gender> getAllGender() {
        return List.of(Gender.values());
    }

    public List<AccountEntity> getAllNumberAccount() {
        return accountRepository.findAll();
    }


    public List<AccountEntity> getAllAccountBalance() {
        return accountRepository.findAll();
    }
}
