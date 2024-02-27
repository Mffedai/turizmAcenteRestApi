package dev.patika.turizmAcente.dao;

import dev.patika.turizmAcente.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {
    Optional<Room> findByHotelAndSessionAndPensionAndType(Integer hotelId, Integer sessionId, Integer pensionId, String type);
}
