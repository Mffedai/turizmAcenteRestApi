package dev.patika.turizmAcente.dto.response.pension;

import dev.patika.turizmAcente.entity.Pension;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PensionResponse {
    private Long id;
    private Pension.Type type;
}
