package by.boitman.database.entity;

import by.boitman.database.entity.enam.Gender;
import by.boitman.database.entity.enam.Role;
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
@Table(name = "users")
public class UserEntity extends CreatableEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "surname", length = 50, nullable = false)
    private String surname;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 10)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 10)
    private Role role;

    //    @Embedded
//    @AttributeOverride(name = "tel", column = @Column(name = "telefon"))
    private String contact;

    @OneToMany(mappedBy = "users")
    private List<AccountEntity> accountEntity = new ArrayList<>();

//    @Builder.Default
//    @ManyToMany
//    @JoinTable(name = "user_account",
//            joinColumns = {
//                    @JoinColumn(name = "account_id")
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(name = "user_id")
//            })
//    private List<AccountEntity> accounts = new ArrayList<>();
//
//    @Builder.Default
//    @ManyToMany
//    @JoinTable(name = "account_card",
//            joinColumns = {
//                    @JoinColumn(name = "account_id")
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(name = "card_id")
//            })
//    private List<CardEntity> cards = new ArrayList<>();

    public void add(UserEntity user) {
    }

    public void remove(UserEntity user) {
    }

    public UserEntity getCards() {
        return null;
    }

    public UserEntity getAccounts() {
        return null;
    }
}

