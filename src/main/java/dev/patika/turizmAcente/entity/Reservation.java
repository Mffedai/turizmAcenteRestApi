package dev.patika.turizmAcente.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation extends BaseEntity {
    @NotNull(message = "Toplam fiyat alanı boş bırakılamaz.")
    @Column(name = "reservation_total_price",nullable = false)
    private int total_price;

    @NotNull(message = "Yetişkin sayısı alanı boş bırakılamaz")
    @Column(name = "reservation_prc_adlt",nullable = false)
    private int adultNumber;

    @NotNull(message = "Çocuk sayısı alanı boş bırakılamaz")
    @Column(name = "reservation_prc_chld",nullable = false)
    private int childNumber;

    @NotNull(message = "Giriş tarihi alanı boş bırakılamaz")
    @Column(name = "reservation_entry_date",nullable = false)
    private LocalDate entryDate;

    @NotNull(message = "Çıkış Tarihi alanı boş bırakılamaz")
    @Column(name = "reservation_exit_date",nullable = false)
    private LocalDate exitDate;

    @ManyToOne()
    @JoinColumn(name = "reservation_room_id")
    private Room room;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "rsrvtn2guests",
            joinColumns = {@JoinColumn(name = "rsrvtn2guests_reservation_id")},
            inverseJoinColumns = {@JoinColumn(name = "rsrvtn2guests_guest_id")}
    )
    private List<Guest> guestList;
}
