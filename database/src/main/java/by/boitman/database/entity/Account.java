package by.boitman.database.entity;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class Account {
    private Long id;
    private String ownerNameAccount;
    private String ownerSurnameAccount;
    private Long accountNumber;
    private Double balanceAccount;
}
