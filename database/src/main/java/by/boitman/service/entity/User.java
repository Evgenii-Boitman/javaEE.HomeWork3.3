package by.boitman.service.entity;

import by.boitman.service.entity.enam.Gender;
import by.boitman.service.entity.enam.Role;
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
