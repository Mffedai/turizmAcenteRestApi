package dev.patika.turizmAcente.dto.response.hotel;

import dev.patika.turizmAcente.entity.Pension;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponse {
    private Long id;
    private String name;
    private String city;
    private String region;
    private String address;
    private String email;
    private String phone;
    private String rate;
    private boolean otopark;
    private boolean wifi;
    private boolean pool;
    private boolean fitness;
    private boolean spa;
    private boolean service;
    private List<Pension> pensionList;
}
