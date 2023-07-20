package by.boitman.database.dto;

import by.boitman.database.entity.UserEntity;
import by.boitman.database.entity.enam.Gender;
import jakarta.persistence.*;
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
    List<Long> usersIds = new ArrayList<>();
}
