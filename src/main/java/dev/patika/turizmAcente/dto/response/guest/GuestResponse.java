package dev.patika.turizmAcente.dto.response.guest;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestResponse {
    private Long id;
    private String name;
    private String phone;
    private String mail;
    private String tc;
}
