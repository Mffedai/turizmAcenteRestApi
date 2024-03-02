package dev.patika.turizmAcente.dto.request.reservation;

import dev.patika.turizmAcente.entity.Guest;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationSaveRequest {
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
    private Long roomId;
    private List<Guest> guestList;
}
