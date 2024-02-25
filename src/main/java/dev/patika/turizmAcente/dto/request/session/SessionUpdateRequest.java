package dev.patika.turizmAcente.dto.request.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionUpdateRequest {
    private Long id;
    private LocalDate strtDate;
    private LocalDate fnshDate;
    private Integer hotelId;
}
