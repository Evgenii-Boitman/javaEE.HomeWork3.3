package by.boitman.database.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CardFilter {
    private String balance;
    private String ownerName;
    private String limit;
    private String page;
    public Float getBalance() {
        return balance == null ? 0.0f : Float.parseFloat(balance);
    }

    public Integer getLimit() {
        return limit == null ? 10 : Integer.parseInt(limit);
    }

    public Integer getOffset() {
        return page == null ? 0 : this.getLimit() * (Integer.parseInt(page) - 1);
    }
}
