package dev.patika.turizmAcente.dto.request.pension;

import dev.patika.turizmAcente.entity.Pension;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PensionUpdateRequest {
    @Positive(message = "ID değeri pozitif olmalıdır")
    private Long id;
    private Pension.Type type;
}
