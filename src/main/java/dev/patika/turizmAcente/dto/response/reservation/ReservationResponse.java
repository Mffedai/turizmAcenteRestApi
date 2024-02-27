package dev.patika.turizmAcente.dto.response.reservation;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse {
    private Long id;
    private int total_price;
    private int adultNumber;
    private int childNumber;
    private LocalDate entry_date;
    private LocalDate exit_date;
}
