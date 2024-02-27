package dev.patika.turizmAcente.business.concretes;

import dev.patika.turizmAcente.business.abstracts.IHotelService;
import dev.patika.turizmAcente.business.abstracts.IPensionService;
import dev.patika.turizmAcente.business.abstracts.IRoomService;
import dev.patika.turizmAcente.business.abstracts.ISessionService;
import dev.patika.turizmAcente.core.config.modelMapper.IModelMapperService;
import dev.patika.turizmAcente.core.exception.DataAlreadyExistException;
import dev.patika.turizmAcente.core.exception.NotFoundException;
import dev.patika.turizmAcente.core.result.ResultData;
import dev.patika.turizmAcente.core.utilies.Msg;
import dev.patika.turizmAcente.core.utilies.ResultHelper;
import dev.patika.turizmAcente.dao.RoomRepo;
import dev.patika.turizmAcente.dto.request.room.RoomSaveRequest;
import dev.patika.turizmAcente.dto.response.room.RoomResponse;
import dev.patika.turizmAcente.entity.Hotel;
import dev.patika.turizmAcente.entity.Pension;
import dev.patika.turizmAcente.entity.Room;
import dev.patika.turizmAcente.entity.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomManager implements IRoomService {

    private final RoomRepo roomRepo;
    private final IModelMapperService modelMapperService;
    private final IPensionService pensionService;
    private final IHotelService hotelService;
    private final ISessionService sessionService;
    @Override
    public ResultData<RoomResponse> save(RoomSaveRequest roomSaveRequest) {
        Optional<Room> roomList = this.roomRepo.findByHotelAndSessionAndPensionAndType(
                roomSaveRequest.getHotelId(),
                roomSaveRequest.getSessionId(),
                roomSaveRequest.getPensionId(),
                roomSaveRequest.getType().toString()
        );
        if (roomList.isPresent()){
            throw new DataAlreadyExistException(Msg.getEntityForMsg(Room.class));
        }
        Hotel hotel = this.hotelService.get(Long.valueOf(roomSaveRequest.getHotelId()));
        Pension pension = this.pensionService.get(Long.valueOf(roomSaveRequest.getPensionId()));
        Session session = this.sessionService.get(Long.valueOf(roomSaveRequest.getSessionId()));
        roomSaveRequest.setHotelId(null);
        roomSaveRequest.setPensionId(null);
        roomSaveRequest.setSessionId(null);
        Room saveRoom = this.modelMapperService.forRequest().map(roomSaveRequest, Room.class);
        saveRoom.setHotel(hotel);
        saveRoom.setPension(pension);
        saveRoom.setSession(session);
        System.out.println(saveRoom);
        return ResultHelper.created(this.modelMapperService.forResponse().map(this.roomRepo.save(saveRoom), RoomResponse.class));
    }

    @Override
    public Room get(Long id) {
        return this.roomRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Room> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.roomRepo.findAll(pageable);
    }

    @Override
    public Room update(Room room) {
        this.get(room.getId());
        return this.roomRepo.save(room);
    }

    @Override
    public boolean delete(Long id) {
        Room room = this.get(id);
        this.roomRepo.delete(room);
        return true;
    }

    @Override
    public Optional<Room> findByValueForValid(Integer hotelId, Integer sessionId, Integer pensionId, String type) {
        return this.roomRepo.findByHotelAndSessionAndPensionAndType(hotelId, sessionId, pensionId, type);
    }
}
