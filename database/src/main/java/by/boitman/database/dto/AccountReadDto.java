package by.boitman.database.dto;

import by.boitman.database.entity.UserEntity;
import by.boitman.database.entity.enam.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountReadDto {
    private Long id;
    private String ownerNameAccount;
    private String ownerSurnameAccount;
    private Gender gender;
    private Long numberAccount;
    private Integer accountBalance;
//    private List<String> cards;
}
