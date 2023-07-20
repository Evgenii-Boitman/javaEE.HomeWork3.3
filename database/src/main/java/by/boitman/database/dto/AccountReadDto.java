package by.boitman.database.dto;

import by.boitman.database.entity.UserEntity;
import by.boitman.database.entity.enam.Gender;
import jakarta.persistence.*;

public class AccountReadDto {
    Long id;
    String ownerNameAccount;
    String ownerSurnameAccount;
    Gender gender;
    Long numberAccount;
    Float accountBalance;

}
