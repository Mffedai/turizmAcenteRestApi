package dev.patika.turizmAcente.entity;

import dev.patika.turizmAcente.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "hotels")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel extends BaseEntity {
    @NotNull
    @Column(name = "hotel_name", nullable = false)
    private String name;
    @NotNull
    @Column(name = "hotel_city", nullable = false)
    private String city;
    @NotNull
    @Column(name = "hotel_region", nullable = false)
    private String region;
    @NotNull
    @Column(name = "hotel_address", nullable = false)
    private String address;
    @NotNull
    @Column(name = "hotel_email", nullable = false)
    private String email;
    @NotNull
    @Column(name = "hotel_phone", nullable = false)
    private String phone;
    @NotNull
    @Column(name = "hotel_rate", nullable = false)
    private String rate;
    @NotNull
    @Column(name = "hotel_otopark", nullable = false)
    private boolean otopark;
    @NotNull
    @Column(name = "hotel_wifi", nullable = false)
    private boolean wifi;
    @NotNull
    @Column(name = "hotel_pool", nullable = false)
    private boolean pool;
    @NotNull
    @Column(name = "hotel_fitness", nullable = false)
    private boolean fitness;
    @NotNull
    @Column(name = "hotel_spa", nullable = false)
    private boolean spa;
    @NotNull
    @Column(name = "hotel_service", nullable = false)
    private boolean service;
}
