package TestApp.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class Payment {
    private Long id;
    @NotBlank
    private Double operationAmount;
    private Long bonusId;
    private Long accountId;
    private String operationType;
    public Map<String, Object> toMap() {
        Map<String, Object> values = new HashMap<>();
        values.put("operation_amount", operationAmount);
        values.put("account_id", accountId);
        values.put("bonus_id", bonusId);
        values.put("operation_type", operationType);
        return values;
    }
    public double onlineOperation() {
        return (operationAmount * 0.17);
    }

    public double shopOperation() {
        return (operationAmount * 0.1);
    }
}
