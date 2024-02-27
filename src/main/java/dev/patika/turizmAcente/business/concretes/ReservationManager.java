package dev.patika.turizmAcente.business.concretes;

import dev.patika.turizmAcente.business.abstracts.IReservationService;
import dev.patika.turizmAcente.business.abstracts.IRoomService;
import dev.patika.turizmAcente.core.config.modelMapper.IModelMapperService;
import dev.patika.turizmAcente.core.exception.DataAlreadyExistException;
import dev.patika.turizmAcente.core.exception.NotFoundException;
import dev.patika.turizmAcente.core.result.ResultData;
import dev.patika.turizmAcente.core.utilies.Msg;
import dev.patika.turizmAcente.core.utilies.ResultHelper;
import dev.patika.turizmAcente.dao.ReservationRepo;
import dev.patika.turizmAcente.dto.request.reservation.ReservationSaveRequest;
import dev.patika.turizmAcente.dto.response.reservation.ReservationResponse;
import dev.patika.turizmAcente.entity.Guest;
import dev.patika.turizmAcente.entity.Reservation;
import dev.patika.turizmAcente.entity.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationManager implements IReservationService {
    private final ReservationRepo reservationRepo;
    private final IModelMapperService modelMapperService;
    private final IRoomService roomService;
    @Override
    public ResultData<ReservationResponse> save(ReservationSaveRequest reservationSaveRequest) {
        Optional<Reservation> reservationOptional = this.findByValueForValid(
                reservationSaveRequest.getRoom_id(),
                reservationSaveRequest.getEntry_date(),
                reservationSaveRequest.getExit_date(),
                reservationSaveRequest.getGuestList()
        );
        if (reservationOptional.isPresent()){
            throw new DataAlreadyExistException(Msg.getEntityForMsg(Reservation.class));
        }
        Room room = this.roomService.get(Long.valueOf(reservationSaveRequest.getRoom_id()));
        reservationSaveRequest.setRoom_id(null);
        Reservation saveReservation = this.modelMapperService.forRequest().map(reservationSaveRequest, Reservation.class);
        saveReservation.setRoom(room);
        return ResultHelper.created(this.modelMapperService.forResponse().map(this.reservationRepo.save(saveReservation), ReservationResponse.class));
    }
    @Override
    public Reservation get(Long id) {
        return this.reservationRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Reservation> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.reservationRepo.findAll(pageable);
    }

    @Override
    public Reservation update(Reservation reservation) {
        this.get(reservation.getId());
        return this.reservationRepo.save(reservation);
    }

    @Override
    public boolean delete(Long id) {
        Reservation reservation = this.get(id);
        this.reservationRepo.delete(reservation);
        return true;
    }

    @Override
    public Optional<Reservation> findByValueForValid(Integer roomId, LocalDate entry_date, LocalDate exit_date, List<Guest> guestList) {
        return this.reservationRepo.findByRoomAndEntry_dateAndExit_dateAndGuestList(roomId, entry_date, exit_date, guestList);
    }
}
