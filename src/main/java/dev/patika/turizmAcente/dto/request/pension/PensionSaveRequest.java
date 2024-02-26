package dev.patika.turizmAcente.dto.request.pension;

import dev.patika.turizmAcente.entity.Pension;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PensionSaveRequest {
    @NotNull(message = "Pansiyon tipi boş olamaz.")
    private Pension.Type type;
}
