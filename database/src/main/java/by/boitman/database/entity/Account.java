package by.boitman.database.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(of = "id")
@Data
@Builder
public class Account {
    private Long id;
    private String ownerNameAccount;
    private String ownerSurnameAccount;
    private Long numberAccount;
    private Double balanceAccount;
    @Builder.Default
    private List<User> users = new ArrayList<>();
}
