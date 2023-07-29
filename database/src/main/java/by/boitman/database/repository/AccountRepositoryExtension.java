package by.boitman.database.repository;

import by.boitman.database.dto.AccountFilter;
import by.boitman.database.entity.AccountEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepositoryExtension {
    List<AccountEntity> findByFilter(AccountFilter filter);
}
