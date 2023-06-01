package by.boitman.database.entity;

import by.boitman.database.entity.enam.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(of = "id")
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
    private Double accountBalance;

    @Builder.Default
    @ManyToMany(mappedBy = "accounts")
    private List<UserEntity> users = new ArrayList<>();

    public void addUser(UserEntity user) {
        this.getUsers().add(user);
        user.getAccounts().add(this);
    }

    public void removeUser(UserEntity user) {
        this.getUsers().remove(user);
        user.getAccounts().remove(this);
    }

}
