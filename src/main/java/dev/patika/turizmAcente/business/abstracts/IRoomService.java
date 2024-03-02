package dev.patika.turizmAcente.business.abstracts;

import dev.patika.turizmAcente.dto.request.room.RoomSaveRequest;
import dev.patika.turizmAcente.dto.request.room.RoomUpdateRequest;
import dev.patika.turizmAcente.dto.response.room.RoomResponse;
import dev.patika.turizmAcente.entity.Hotel;
import dev.patika.turizmAcente.entity.Pension;
import dev.patika.turizmAcente.entity.Room;
import dev.patika.turizmAcente.entity.Session;

import java.util.Optional;

public interface IRoomService extends IBaseService<Room, RoomSaveRequest, RoomUpdateRequest, RoomResponse>{
    Optional<Room> findByValueForValid(Hotel hotel, Session session, Pension pension, Room.Type type);

}
