package dev.patika.turizmAcente.business.abstracts;

import dev.patika.turizmAcente.dto.request.hotel.HotelSaveRequest;
import dev.patika.turizmAcente.dto.response.hotel.HotelResponse;
import dev.patika.turizmAcente.entity.Hotel;

public interface IHotelService extends IBaseService<Hotel, HotelSaveRequest, HotelResponse>{

}
