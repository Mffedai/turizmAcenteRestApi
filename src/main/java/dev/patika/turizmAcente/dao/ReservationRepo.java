package dev.patika.turizmAcente.dao;

import dev.patika.turizmAcente.entity.Guest;
import dev.patika.turizmAcente.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByRoomAndEntry_dateAndExit_dateAndGuestList(Integer roomId, LocalDate entry_date, LocalDate exit_date, List<Guest> guestList);
}
