package dev.patika.turizmAcente.dao;

import dev.patika.turizmAcente.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
    List<Users> findByRole(Users.Role role);
    Users findByName(String username);
}
