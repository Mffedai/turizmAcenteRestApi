package dev.patika.turizmAcente.business.abstracts;

import dev.patika.turizmAcente.dto.request.room.RoomSaveRequest;
import dev.patika.turizmAcente.dto.response.room.RoomResponse;
import dev.patika.turizmAcente.entity.Room;

public interface IRoomService extends IBaseService<Room, RoomSaveRequest, RoomResponse>{
}
