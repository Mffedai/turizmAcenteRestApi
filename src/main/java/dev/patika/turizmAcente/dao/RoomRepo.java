package dev.patika.turizmAcente.dao;

import dev.patika.turizmAcente.entity.Hotel;
import dev.patika.turizmAcente.entity.Pension;
import dev.patika.turizmAcente.entity.Room;
import dev.patika.turizmAcente.entity.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {
    Optional<Room> findByHotelAndSessionAndPensionAndType(Hotel hotel, Session session, Pension pension, Room.Type type);
    Page<Room> findByStockGreaterThan(int stock, Pageable pageable);
}
