package dev.patika.turizmAcente.business.concretes;

import dev.patika.turizmAcente.business.abstracts.IHotelService;
import dev.patika.turizmAcente.core.config.modelMapper.IModelMapperService;
import dev.patika.turizmAcente.core.exception.NotFoundException;
import dev.patika.turizmAcente.core.result.ResultData;
import dev.patika.turizmAcente.core.utilies.Msg;
import dev.patika.turizmAcente.core.utilies.ResultHelper;
import dev.patika.turizmAcente.dao.HotelRepo;
import dev.patika.turizmAcente.dto.request.hotel.HotelSaveRequest;
import dev.patika.turizmAcente.dto.response.hotel.HotelResponse;
import dev.patika.turizmAcente.entity.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelManager implements IHotelService {
    private final HotelRepo hotelRepo;
    private final IModelMapperService modelMapperService;

    @Override
    public ResultData<HotelResponse> save(HotelSaveRequest hotelSaveRequest) {
        Hotel saveHotel = this.modelMapperService.forRequest().map(hotelSaveRequest, Hotel.class);
        return ResultHelper.created(this.modelMapperService.forResponse().map(saveHotel, HotelResponse.class));
    }

    @Override
    public Hotel get(Long id) {
        return this.hotelRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Hotel> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.hotelRepo.findAll(pageable);
    }

    @Override
    public Hotel update(Hotel hotel) {
        this.get(hotel.getId());
        return this.hotelRepo.save(hotel);
    }

    @Override
    public boolean delete(Long id) {
        Hotel hotel = this.get(id);
        this.hotelRepo.delete(hotel);
        return true;
    }
}
