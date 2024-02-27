package dev.patika.turizmAcente.dao;

import dev.patika.turizmAcente.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
    Users findByNameAndPassword(String name, String password);

    Users findByName(String username);
}
