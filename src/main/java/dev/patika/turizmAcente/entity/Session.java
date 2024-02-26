package dev.patika.turizmAcente.entity;

import dev.patika.turizmAcente.core.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "sessions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Session extends BaseEntity {
    @NotNull
    @Column(name = "session_strtdate", nullable = false)
    private LocalDate strtDate;
    @NotNull
    @Column(name = "session_fnshdate", nullable = false)
    private LocalDate fnshDate;

    @ManyToOne()
    @JoinColumn(name = "session_hotel_id")
    private Hotel hotel;
}
