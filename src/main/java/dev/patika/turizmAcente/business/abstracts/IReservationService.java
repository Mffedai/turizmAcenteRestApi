package dev.patika.turizmAcente.business.abstracts;

import dev.patika.turizmAcente.dto.request.reservation.ReservationSaveRequest;
import dev.patika.turizmAcente.dto.response.reservation.ReservationResponse;
import dev.patika.turizmAcente.entity.Guest;
import dev.patika.turizmAcente.entity.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IReservationService extends IBaseService<Reservation, ReservationSaveRequest, ReservationResponse>{
    Optional<Reservation> findByValueForValid(Integer roomId, LocalDate entry_date, LocalDate exit_date, List<Guest> guestList);
}
