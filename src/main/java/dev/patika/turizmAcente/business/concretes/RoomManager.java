package dev.patika.turizmAcente.business.concretes;

import dev.patika.turizmAcente.business.abstracts.IRoomService;
import dev.patika.turizmAcente.core.config.modelMapper.IModelMapperService;
import dev.patika.turizmAcente.core.exception.NotFoundException;
import dev.patika.turizmAcente.core.result.ResultData;
import dev.patika.turizmAcente.core.utilies.Msg;
import dev.patika.turizmAcente.core.utilies.ResultHelper;
import dev.patika.turizmAcente.dao.RoomRepo;
import dev.patika.turizmAcente.dto.request.room.RoomSaveRequest;
import dev.patika.turizmAcente.dto.response.room.RoomResponse;
import dev.patika.turizmAcente.entity.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomManager implements IRoomService {

    private final RoomRepo roomRepo;
    private final IModelMapperService modelMapperService;
    @Override
    public ResultData<RoomResponse> save(RoomSaveRequest roomSaveRequest) {
        Room saveRoom = this.modelMapperService.forRequest().map(roomSaveRequest, Room.class);
        return ResultHelper.created(this.modelMapperService.forResponse().map(saveRoom, RoomResponse.class));
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
}
