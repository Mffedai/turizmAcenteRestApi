package dev.patika.turizmAcente.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users extends BaseEntity {
    @NotNull
    @Column(name = "user_name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "user_password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private Role role;

    public enum Role {
        ADMIN,
        EMPLOYEE
    }

}
