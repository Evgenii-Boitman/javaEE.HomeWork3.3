package by.boitman.database.entity;

import lombok.Builder;

@Builder
public class BankingAccount {
    private String bankingAccountId;
    private String ownerBankingAccount;

    public String getBankingAccountId() {
        return bankingAccountId;
    }

    public void setBankingAccountId(String bankingAccountId) {
        this.bankingAccountId = bankingAccountId;
    }

    public String getOwnerBankingAccount() {
        return ownerBankingAccount;
    }

    public void setOwnerBankingAccount(String ownerBankingAccount) {
        this.ownerBankingAccount = ownerBankingAccount;
    }
}
