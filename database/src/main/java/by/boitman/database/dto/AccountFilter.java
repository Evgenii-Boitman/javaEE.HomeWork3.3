package by.boitman.database.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AccountFilter {

    private String accountBalance;
    private String limit;
    private String userName;
    private String page;
    public Integer getAccountBalance() {
        return accountBalance == null ? 1000 : Integer.parseInt(accountBalance);
    }

//    public Float getAccountBalance() {
//        return accountBalance == null ? 0.0f : Float.parseFloat(accountBalance);
//    }

    public Integer getLimit() {
        return limit == null ? 10 : Integer.parseInt(limit);
    }

    public Integer getOffset() {
        return page == null ? 0 : this.getLimit() * (Integer.parseInt(page) - 1);
    }
}
