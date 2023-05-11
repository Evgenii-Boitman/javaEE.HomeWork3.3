package by.boitman.service.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(of = "id")
@Data
@Builder
public class Card {
    private Long id;
    private String ownerName;
    private String ownerSurname;
    private String dateCard;
    private Long cardNumber;
    private Double balance;
    @Builder.Default
    private List<User> users = new ArrayList<>();

}
