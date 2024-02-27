package dev.patika.turizmAcente.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room extends BaseEntity {
    @NotNull
    @Column(name = "room_stock", nullable = false)
    private int stock;
    @NotNull
    @Column(name = "room_bed", nullable = false)
    private int bed;
    @NotNull
    @Column(name = "room_mtrsqr", nullable = false)
    private int mtrsqr;
    @NotNull
    @Column(name = "room_prc_chld", nullable = false)
    private int prc_chld;
    @NotNull
    @Column(name = "room_prc_adult", nullable = false)
    private int prc_adult;
    @NotNull
    @Column(name = "room_aircndtn", nullable = false)
    private boolean aircndtn;
    @NotNull
    @Column(name = "room_minibar", nullable = false)
    private boolean minibar;
    @NotNull
    @Column(name = "room_tv", nullable = false)
    private boolean tv;
    @NotNull
    @Column(name = "room_safe", nullable = false)
    private boolean safe;
    @NotNull
    @Column(name = "room_fridge", nullable = false)
    private boolean fridge;

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservationList;

    @ManyToOne()
    @JoinColumn(name = "room_hotel_id")
    private Hotel hotel;

    @ManyToOne()
    @JoinColumn(name = "room_pension_id")
    private Pension pension;

    @ManyToOne()
    @JoinColumn(name = "room_session_id")
    private Session session;

    private Room.Type type;
    public enum Type{
        SingleRoom,
        DoubleRoom,
        JuniorSuiteRoom,
        SuiteRoom
    }
}
