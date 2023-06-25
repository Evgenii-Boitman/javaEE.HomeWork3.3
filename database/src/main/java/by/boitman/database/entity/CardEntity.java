package by.boitman.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;


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
    @Cascade(SAVE_UPDATE)
    @JoinColumn(name = "account_id")
    private AccountEntity accounts;

}
