package dev.patika.turizmAcente.dao;

import dev.patika.turizmAcente.entity.Hotel;
import dev.patika.turizmAcente.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SessionRepo extends JpaRepository<Session, Long> {
    Optional<Session> findByHotelAndStrtDateAndFnshDate(Hotel hotel, LocalDate strt_date, LocalDate fnsh_date);
}
