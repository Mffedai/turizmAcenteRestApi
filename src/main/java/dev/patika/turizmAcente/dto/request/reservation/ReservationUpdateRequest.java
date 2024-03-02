package dev.patika.turizmAcente.dto.request.reservation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationUpdateRequest {
    @Positive(message = "ID değeri pozitif olmalı")
    private Long id;
    @NotNull(message = "Toplam fiyat alanı boş bırakılamaz.")
    private int total_price;
    @NotNull(message = "Yetişkin sayısı alanı boş bırakılamaz")
    private int adultNumber;
    @NotNull(message = "Çocuk sayısı alanı boş bırakılamaz")
    private int childNumber;
    @NotNull(message = "Giriş tarihi alanı boş bırakılamaz")
    private LocalDate entryDate;
    @NotNull(message = "Çıkış Tarihi alanı boş bırakılamaz")
    private LocalDate exitDate;
}
