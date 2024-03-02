package dev.patika.turizmAcente.business.abstracts;

import dev.patika.turizmAcente.dto.request.reservation.ReservationSaveRequest;
import dev.patika.turizmAcente.dto.request.reservation.ReservationUpdateRequest;
import dev.patika.turizmAcente.dto.response.reservation.ReservationResponse;
import dev.patika.turizmAcente.entity.Guest;
import dev.patika.turizmAcente.entity.Reservation;
import dev.patika.turizmAcente.entity.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IReservationService extends IBaseService<Reservation, ReservationSaveRequest, ReservationUpdateRequest, ReservationResponse>{
    Optional<Reservation> findByRoomIdAndEntry_dateAndExit_date(Long roomId, LocalDate entry, LocalDate exit);
}
