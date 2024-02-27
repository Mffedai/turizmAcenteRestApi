package dev.patika.turizmAcente.dao;

import dev.patika.turizmAcente.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Long> {
    Optional<Hotel> findByNameAndAddressAndCityAndRegionAndPhone(String name, String address, String city, String region, String phone);

}
