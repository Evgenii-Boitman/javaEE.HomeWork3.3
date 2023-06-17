package by.boitman.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static by.boitman.database.entity.AccountEntity_.users;


@Data
@Builder
@EqualsAndHashCode(of = "id", callSuper = false)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card")
public class CardEntity extends CreatableEntity<Long> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String ownerName;

    @Column(name = "surname", length = 50, nullable = false)
    private String ownerSurname;

    @Column(name = "card_number", nullable = false)
    private Long cardNumber;

    @Column(name = "card_balance", nullable = false)
    private Float balance;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity accounts;

    public void addUser(UserEntity user) {
        this.getUsers().add(user);
        user.getCards().add(this);
    }

    private UserEntity getUsers() {
        return (UserEntity) users;
    }

    public void removeUser(UserEntity user) {
        this.getUsers().remove(user);
        user.getCards().remove(this);
    }

}
