package dev.patika.turizmAcente.dto.request.user;

import dev.patika.turizmAcente.entity.Users;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSaveRequest {
    @NotNull(message = "Kullanıcı adı boş olamaz.")
    private String name;

    @NotNull(message = "Kullanıcı parolası boş olamaz")
    private String password;

    @NotNull(message = "Kullanıcı rolü boş olamaz.")
    private Users.Role role;

}
