package dev.patika.turizmAcente.business.abstracts;

import dev.patika.turizmAcente.dto.request.hotel.HotelSaveRequest;
import dev.patika.turizmAcente.dto.request.hotel.HotelUpdateRequest;
import dev.patika.turizmAcente.dto.response.hotel.HotelResponse;
import dev.patika.turizmAcente.entity.Hotel;

import java.util.Optional;

public interface IHotelService extends IBaseService<Hotel, HotelSaveRequest, HotelUpdateRequest, HotelResponse>{
    Optional<Hotel> findByNameAndAddressAndCityAndRegionAndPhone(String name, String address, String city, String region, String phone);
}
