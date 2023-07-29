package by.boitman.database.dto;

import by.boitman.database.entity.enam.Gender;
import by.boitman.database.entity.enam.Role;

public record UserRecord(Long id, String name, String surname, String email, Gender gender, Role role, String contact) {
}
