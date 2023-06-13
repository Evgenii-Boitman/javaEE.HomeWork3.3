package by.boitman.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


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
    private AccountEntity accountEntity;

    @Builder.Default
    @ManyToMany(mappedBy = "cards")
    private List<UserEntity> users = new ArrayList<>();

    public void addUser(UserEntity user) {
        this.getUsers().add(user);
        user.getCards().add(this.getUsers());
    }

    private UserEntity getUsers() {
        return null;
    }

    public void removeUser(UserEntity user) {
        this.getUsers().remove(user);
        user.getCards().remove(this.getUsers());
    }
}
