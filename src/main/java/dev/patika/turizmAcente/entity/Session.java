package dev.patika.turizmAcente.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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

    @OneToMany(mappedBy = "session")
    @JsonIgnore
    private List<Room> roomList;
    @ManyToOne()
    @JoinColumn(name = "session_hotel_id")
    private Hotel hotel;
}
