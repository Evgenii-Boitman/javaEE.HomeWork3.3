package by.boitman.database.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AccountFilter {

    private Double accountBalance;
    private Integer limit;
    private String userName;
    private Integer page;
    public Integer getLimit() {
        return limit == null ? 10 : limit;
    }

    public Integer getOffset() {
        return page == null ? 0 : limit * (page - 1);
    }
}
