package dev.patika.turizmAcente.business.concretes;

import dev.patika.turizmAcente.business.abstracts.IHotelService;
import dev.patika.turizmAcente.core.config.modelMapper.IModelMapperService;
import dev.patika.turizmAcente.core.exception.DataAlreadyExistException;
import dev.patika.turizmAcente.core.exception.NotFoundException;
import dev.patika.turizmAcente.core.result.ResultData;
import dev.patika.turizmAcente.core.utilies.Msg;
import dev.patika.turizmAcente.core.utilies.ResultHelper;
import dev.patika.turizmAcente.dao.HotelRepo;
import dev.patika.turizmAcente.dto.CursorResponse;
import dev.patika.turizmAcente.dto.request.hotel.HotelSaveRequest;
import dev.patika.turizmAcente.dto.request.hotel.HotelUpdateRequest;
import dev.patika.turizmAcente.dto.response.hotel.HotelResponse;
import dev.patika.turizmAcente.entity.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelManager implements IHotelService {
    private final HotelRepo hotelRepo;
    private final IModelMapperService modelMapperService;

    @Override
    public ResultData<HotelResponse> save(HotelSaveRequest hotelSaveRequest) {
        Optional<Hotel> hotelList = this.findByNameAndAddressAndCityAndRegionAndPhone(
                hotelSaveRequest.getName(),
                hotelSaveRequest.getAddress(),
                hotelSaveRequest.getCity(),
                hotelSaveRequest.getRegion(),
                hotelSaveRequest.getPhone()
        );
        if (hotelList.isPresent()){
            throw new DataAlreadyExistException(Msg.getEntityForMsg(Hotel.class));
        }
        Hotel saveHotel = this.modelMapperService.forRequest().map(hotelSaveRequest, Hotel.class);
        return ResultHelper.created(this.modelMapperService.forResponse().map(this.hotelRepo.save(saveHotel), HotelResponse.class));
    }

    @Override
    public Hotel get(Long id) {
        return this.hotelRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public ResultData<CursorResponse<HotelResponse>> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Hotel> hotelPage =  this.hotelRepo.findAll(pageable);
        Page<HotelResponse> hotelResponsePage = hotelPage.map(hotel -> this.modelMapperService.forResponse().map(hotel, HotelResponse.class));
        return ResultHelper.cursor(hotelResponsePage);
    }

    @Override
    public ResultData<HotelResponse> update(HotelUpdateRequest hotelUpdateRequest) {
        this.get(hotelUpdateRequest.getId());
        Hotel updateHotel = this.modelMapperService.forRequest().map(hotelUpdateRequest, Hotel.class);
        return ResultHelper.success(this.modelMapperService.forResponse().map(this.hotelRepo.save(updateHotel), HotelResponse.class));
    }

    @Override
    public boolean delete(Long id) {
        Hotel hotel = this.get(id);
        this.hotelRepo.delete(hotel);
        return true;
    }

    @Override
    public Optional<Hotel> findByNameAndAddressAndCityAndRegionAndPhone(String name, String address, String city, String region, String phone) {
        return this.hotelRepo.findByNameAndAddressAndCityAndRegionAndPhone(name, address, city, region, phone);
    }
}
