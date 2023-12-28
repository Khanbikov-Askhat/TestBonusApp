package TestApp.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class BankAccount {
    private Long id;
    @NotBlank
    private Double amount;

    public Map<String, Object> toMap() {
        Map<String, Object> values = new HashMap<>();
        values.put("amount", amount);
        return values;
    }
}
