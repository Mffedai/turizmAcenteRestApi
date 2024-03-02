package dev.patika.turizmAcente.dto.request.guest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestSaveRequest {
    private String name;
    private String phone;
    private String mail;
    private String tc;
}
