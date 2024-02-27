package dev.patika.turizmAcente.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "guests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Guest extends BaseEntity {
    @Column(name = "guest_name")
    private String name;
    @Column(name = "guest_phone")
    private String phone;
    @Column(name = "guest_mail")
    private String mail;
    @Column(name = "guest_tc")
    private String tc;

    @ManyToMany(mappedBy = "guestList")
    @JsonIgnore
    private List<Reservation> reservationList;
}
