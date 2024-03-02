package dev.patika.turizmAcente.api;

import dev.patika.turizmAcente.business.abstracts.IHotelService;
import dev.patika.turizmAcente.core.config.modelMapper.IModelMapperService;
import dev.patika.turizmAcente.core.result.Result;
import dev.patika.turizmAcente.core.result.ResultData;
import dev.patika.turizmAcente.core.utilies.ResultHelper;
import dev.patika.turizmAcente.dto.CursorResponse;
import dev.patika.turizmAcente.dto.request.hotel.HotelSaveRequest;
import dev.patika.turizmAcente.dto.request.hotel.HotelUpdateRequest;
import dev.patika.turizmAcente.dto.response.hotel.HotelResponse;
import dev.patika.turizmAcente.entity.Hotel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/hotels")
@RequiredArgsConstructor
public class HotelController {
    private final IHotelService hotelService;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<HotelResponse> save(@Valid @RequestBody HotelSaveRequest hotelSaveRequest){
        return this.hotelService.save(hotelSaveRequest);
    }
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<HotelResponse> update(@Valid @RequestBody HotelUpdateRequest hotelUpdateRequest){
        return this.hotelService.update(hotelUpdateRequest);
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<HotelResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return this.hotelService.cursor(page, pageSize);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){
        this.hotelService.delete(id);
        return ResultHelper.ok();
    }
}
