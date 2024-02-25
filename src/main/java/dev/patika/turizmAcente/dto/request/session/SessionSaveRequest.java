package dev.patika.turizmAcente.dto.request.session;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionSaveRequest {
    @NotNull(message = "Sezon başlangıç tarihi boş olamaz")
    private LocalDate strtDate;
    @NotNull(message = "Sezon bitiş tarihi boş olamaz")
    private LocalDate fnshDate;
    @NotNull(message = "Hotel id alanı boş olamaz")
    private Integer hotelId;
}
