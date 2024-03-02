package dev.patika.turizmAcente.dao;

import dev.patika.turizmAcente.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByRoomIdAndEntryDateAndExitDate(Long roomId, LocalDate entryDate, LocalDate exitDate);

}
