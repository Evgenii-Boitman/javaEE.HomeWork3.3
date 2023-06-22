package by.boitman.database.entity;

import by.boitman.database.entity.enam.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;

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
    @Cascade(SAVE_UPDATE)
    @JoinColumn(name = "user_id")
    private UserEntity users;

    @Builder.Default
    @OneToMany(mappedBy = "accounts")
    private List<CardEntity> cards = new ArrayList<>();

}
