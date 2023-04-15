package by.boitman.database.entity;

import lombok.Builder;
import lombok.Data;

//@EqualsAndHashCode(of = "id")
@Data
@Builder
public class CreditCard {
    private Long id;
    private String ownerName;
    private String date;
    private Long cardNumber;
    private Double balance;

}
