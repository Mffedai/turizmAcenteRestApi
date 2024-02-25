package dev.patika.turizmAcente.dto.response.user;

import dev.patika.turizmAcente.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String password;
    private Users.Role role;
}
