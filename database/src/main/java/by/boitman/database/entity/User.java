package by.boitman.database.entity;

import by.boitman.database.entity.enam.Gender;
import by.boitman.database.entity.enam.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = "id")
public class User {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Gender gender;
    private Role role;
    private Contact contact;

}
