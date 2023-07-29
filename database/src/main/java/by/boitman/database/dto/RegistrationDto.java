package by.boitman.database.dto;

import by.boitman.database.entity.enam.Gender;
import by.boitman.database.entity.enam.Role;

import java.time.LocalDate;

public record RegistrationDto(
        String name,
        String surname,
        String email,
        String password,
        String contact,
        Gender gender,
        Role role
//        LocalDate date
) {
}
