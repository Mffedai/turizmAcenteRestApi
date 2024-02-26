package dev.patika.turizmAcente.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.patika.turizmAcente.core.BaseEntity;
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
@Table(name = "pensions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pension extends BaseEntity {
    @Column(name = "pension_name", nullable = false)
    private Type type;

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
