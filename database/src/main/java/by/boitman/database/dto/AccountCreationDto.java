package by.boitman.database.dto;

import by.boitman.database.entity.enam.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreationDto {

    String ownerNameAccount;
    String ownerSurnameAccount;
    Gender gender;
    Long numberAccount;
    Integer accountBalance;
    List<Long> usersIds = new ArrayList<>();
//    List<Long> cardsIds = new ArrayList<>();
}
