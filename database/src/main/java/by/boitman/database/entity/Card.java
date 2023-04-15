package by.boitman.database.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

//@EqualsAndHashCode(of = "id")
@Data
@Builder
public class Card {
    private Long id;
    private String ownerName;
    private String ownerSurname;
    private LocalDate dateCard;
    private Long cardNumber;
    private Double balance;

}
