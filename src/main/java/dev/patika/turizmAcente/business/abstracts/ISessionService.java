package dev.patika.turizmAcente.business.abstracts;

import dev.patika.turizmAcente.dto.request.session.SessionSaveRequest;
import dev.patika.turizmAcente.dto.request.session.SessionUpdateRequest;
import dev.patika.turizmAcente.dto.response.session.SessionResponse;
import dev.patika.turizmAcente.entity.Hotel;
import dev.patika.turizmAcente.entity.Session;

import java.time.LocalDate;
import java.util.Optional;

public interface ISessionService extends IBaseService<Session, SessionSaveRequest, SessionUpdateRequest, SessionResponse> {
    Optional<Session> findByHotelAndStrtDateAndFnshDate(Hotel hotel, LocalDate strt_date, LocalDate fnsh_date);
}
