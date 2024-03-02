package dev.patika.turizmAcente.business.concretes;

import dev.patika.turizmAcente.business.abstracts.IReservationService;
import dev.patika.turizmAcente.business.abstracts.IRoomService;
import dev.patika.turizmAcente.core.config.modelMapper.IModelMapperService;
import dev.patika.turizmAcente.core.exception.DataAlreadyExistException;
import dev.patika.turizmAcente.core.exception.NotFoundException;
import dev.patika.turizmAcente.core.result.ResultData;
import dev.patika.turizmAcente.core.utilies.Msg;
import dev.patika.turizmAcente.core.utilies.ResultHelper;
import dev.patika.turizmAcente.dao.GuestRepo;
import dev.patika.turizmAcente.dao.ReservationRepo;
import dev.patika.turizmAcente.dao.RoomRepo;
import dev.patika.turizmAcente.dto.CursorResponse;
import dev.patika.turizmAcente.dto.request.reservation.ReservationSaveRequest;
import dev.patika.turizmAcente.dto.request.reservation.ReservationUpdateRequest;
import dev.patika.turizmAcente.dto.request.room.RoomSaveRequest;
import dev.patika.turizmAcente.dto.response.reservation.ReservationResponse;
import dev.patika.turizmAcente.entity.Guest;
import dev.patika.turizmAcente.entity.Reservation;
import dev.patika.turizmAcente.entity.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationManager implements IReservationService {
    private final ReservationRepo reservationRepo;
    private final IModelMapperService modelMapperService;
    private final IRoomService roomService;
    @Transactional
    @Override
    public ResultData<ReservationResponse> save(ReservationSaveRequest reservationSaveRequest) {
        Room room = this.roomService.get(reservationSaveRequest.getRoomId());
        if (room == null){
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        Optional<Reservation> reservationOptional = this.findByRoomIdAndEntry_dateAndExit_date(
                reservationSaveRequest.getRoomId(),
                reservationSaveRequest.getEntryDate(),
                reservationSaveRequest.getExitDate()
        );
        if (reservationOptional.isPresent()){
            throw new DataAlreadyExistException(Msg.getEntityForMsg(Reservation.class));
        }
        List<Guest> guests = reservationSaveRequest.getGuestList();
        reservationSaveRequest.setRoomId(null);
        reservationSaveRequest.setGuestList(null);
        Reservation saveReservation = this.modelMapperService.forRequest().map(reservationSaveRequest, Reservation.class);
        saveReservation.setGuestList(guests);
        saveReservation.setRoom(room);
        room.setStock(room.getStock() - 1);
        RoomSaveRequest roomSaveRequest = this.modelMapperService.forRequest().map(room, RoomSaveRequest.class);
        this.roomService.save(roomSaveRequest);
        return ResultHelper.created(this.modelMapperService.forResponse().map(this.reservationRepo.save(saveReservation), ReservationResponse.class));
    }
    @Override
    public Reservation get(Long id) {
        return this.reservationRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }
    @Override
    public ResultData<CursorResponse<ReservationResponse>> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Reservation> reservationPage = this.reservationRepo.findAll(pageable);
        Page<ReservationResponse> reservationResponses = reservationPage.map(reservation -> this.modelMapperService.forResponse().map(reservation, ReservationResponse.class));
        return ResultHelper.cursor(reservationResponses);
    }
    @Override
    public ResultData<ReservationResponse> update(ReservationUpdateRequest reservationUpdateRequest) {
        this.get(reservationUpdateRequest.getId());
        Reservation updateReservation = this.modelMapperService.forRequest().map(reservationUpdateRequest, Reservation.class);
        return ResultHelper.success(this.modelMapperService.forResponse().map(this.reservationRepo.save(updateReservation), ReservationResponse.class));
    }
    @Override
    public boolean delete(Long id) {
        Reservation reservation = this.get(id);
        this.reservationRepo.delete(reservation);
        return true;
    }
    @Override
    public Optional<Reservation> findByRoomIdAndEntry_dateAndExit_date(Long roomId, LocalDate entry, LocalDate exit) {
        return this.reservationRepo.findByRoomIdAndEntryDateAndExitDate(roomId, entry, exit);
    }
}
