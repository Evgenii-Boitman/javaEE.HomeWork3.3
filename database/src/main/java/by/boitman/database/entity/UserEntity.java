package by.boitman.database.entity;

import by.boitman.database.entity.enam.Gender;
import by.boitman.database.entity.enam.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "acount_owner",
            joinColumns = {
                    @JoinColumn(name = "account_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id")
            })
    private List<AccountEntity> accounts = new ArrayList<>();

//    @Builder.Default
//    @ManyToMany
//    @JoinTable(name = "acount_card_owner",
//            joinColumns = {
//                    @JoinColumn(name = "card_id")
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(name = "user_id")
//            })
//    private List<CardEntity> cards = new ArrayList<>();
}
