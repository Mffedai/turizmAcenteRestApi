package dev.patika.turizmAcente.dto.response.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionResponse {
    private Long id;
    private LocalDate strtDate;
    private LocalDate fnshDate;
    private Integer hotelId;
}
