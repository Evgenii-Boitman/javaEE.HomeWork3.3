package by.boitman.database.entity;

import by.boitman.database.entity.enam.Gender;
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
@Table(name = "account")
public class AccountEntity extends CreatableEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String ownerNameAccount;

    @Column(name = "surname", length = 50, nullable = false)
    private String ownerSurnameAccount;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 10, nullable = false)
    private Gender gender;

    @Column(name = "number_account", nullable = false)
    private Long numberAccount;

    @Column(name = "account_balance", nullable = false)
    private Float accountBalance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity users;

    @Builder.Default
    @OneToMany(mappedBy = "accounts")
    private List<CardEntity> cards = new ArrayList<>();

//    @JsonIgnore
//    public UserEntity getUsers() {
//        return users;
//    }
    public void addUser(UserEntity user) {
        this.getUsers().add(user);
        user.getAccounts().add(this);
    }

    public void removeUser(UserEntity user) {
        this.getUsers().remove(user);
        user.getAccounts().remove(this);
    }
}
