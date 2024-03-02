package dev.patika.turizmAcente.dao;

import dev.patika.turizmAcente.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepo extends JpaRepository<Guest, Long> {
    Optional<Guest> findByMailAndTc(String mail, String tc);
}
