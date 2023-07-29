package by.boitman.database.repository;

import by.boitman.database.entity.UserEntity;
import by.boitman.database.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long>, AccountRepositoryExtension {


    Optional<AccountEntity> findByNumberAccount(Long numberAccount);

    List<AccountEntity> findAllByUsersContains(UserEntity user);

    @Query(value = "SELECT a FROM AccountEntity a WHERE a.ownerNameAccount LIKE %:ownerNameAccount% AND a.accountBalance < :accountBalance")
    List<AccountEntity> findAllBy(@Param("ownerNameAccount") String ownerNameAccount,
                               @Param("accountBalance") Float accountBalance);

//    @Query(value = "SELECT a.* FROM account a " +
//            "JOIN user_account ua ON a.id = ua.account_id " +
//            "JOIN users u ON u.id = ua.user_id " +
//            "WHERE u.name = ?", nativeQuery = true)
//    List<AccountEntity> findAllBy(String name);

    @Modifying
    @Query("UPDATE AccountEntity a SET a.ownerNameAccount = :ownerNameAccount WHERE a.id = :id")
    void setNumberAccountById(@Param("ownerNameAccount") String ownerNameAccount, @Param("id") Long id);

}
