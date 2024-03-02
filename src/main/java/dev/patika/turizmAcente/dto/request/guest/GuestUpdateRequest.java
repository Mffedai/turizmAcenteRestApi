package dev.patika.turizmAcente.dto.request.guest;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestUpdateRequest {
    @Positive(message = "ID değeri pozitif olmalı")
    private Long id;
    private String name;
    private String phone;
    private String mail;
    private String tc;
}
