package dev.patika.turizmAcente.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "pensions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pension extends BaseEntity {
    @Column(name = "pension_name", nullable = false)
    private Type type;

    @OneToMany(mappedBy = "pension")
    @JsonIgnore
    private List<Room> roomList;

    @ManyToMany(mappedBy = "pensionList")
    @JsonIgnore
    private List<Hotel> hotelList;
    public enum Type{
        UltraHerŞeyDahil,
        HerŞeyDahil,
        OdaKahvaltı,
        TamPansiyon,
        YarımPansiyon,
        SadeceYatak,
        AlkolHariçFullCredit
    }
}
