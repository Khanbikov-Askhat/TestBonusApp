package TestApp.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class BonusMoney {
    private Long id;
    @NotBlank
    private Double bonusAmount;
    private Long accountId;
    public Map<String, Object> toMap() {
        Map<String, Object> values = new HashMap<>();
        values.put("bonus_amount", bonusAmount);
        return values;
    }
}
