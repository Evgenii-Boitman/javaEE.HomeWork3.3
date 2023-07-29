package by.boitman.database.repository;

import by.boitman.database.entity.AccountEntity;
import by.boitman.database.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long>, CardRepositoryExtension {

    Optional<CardEntity> findByCardNumber(Long cardNumber);

    List<CardEntity> findAllByAccountsContains(AccountEntity account);

    @Query(value = "SELECT c FROM CardEntity c WHERE c.ownerName LIKE %:ownerName% AND c.balance < :balance")
    List<CardEntity> findAllBy(@Param("ownerName") String ownerName,
                               @Param("balance") Float balance);

//    @Query(value = "SELECT c.* FROM card c " +
//            "JOIN account_card ac ON c.id = ac.card_id " +
//            "JOIN account a ON a.id = ac.account_id " +
//            "WHERE a.name = ?", nativeQuery = true)
//    List<AccountEntity> findAllBy(String name);

    @Modifying
    @Query("UPDATE CardEntity c SET c.ownerName = :ownerName WHERE c.id = :id")
    void setTitleById(@Param("ownerName") String ownerName, @Param("id") Long id);
}
