package dev.patika.turizmAcente.api;

import dev.patika.turizmAcente.business.abstracts.IReservationService;
import dev.patika.turizmAcente.business.abstracts.IRoomService;
import dev.patika.turizmAcente.core.config.modelMapper.IModelMapperService;
import dev.patika.turizmAcente.core.result.Result;
import dev.patika.turizmAcente.core.result.ResultData;
import dev.patika.turizmAcente.core.utilies.ResultHelper;
import dev.patika.turizmAcente.dto.CursorResponse;
import dev.patika.turizmAcente.dto.request.reservation.ReservationSaveRequest;
import dev.patika.turizmAcente.dto.request.reservation.ReservationUpdateRequest;
import dev.patika.turizmAcente.dto.response.reservation.ReservationResponse;
import dev.patika.turizmAcente.entity.Reservation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final IReservationService reservationService;
    private final IModelMapperService modelMapperService;
    private final IRoomService roomService;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<ReservationResponse> save(@Valid @RequestBody ReservationSaveRequest reservationSaveRequest){

        return this.reservationService.save(reservationSaveRequest);
    }
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<ReservationResponse> update(@Valid @RequestBody ReservationUpdateRequest reservationUpdateRequest){
        this.reservationService.get(reservationUpdateRequest.getId());
        Reservation updateReservation = this.modelMapperService.forRequest().map(reservationUpdateRequest, Reservation.class);
        this.reservationService.update(updateReservation);
        return ResultHelper.success(this.modelMapperService.forResponse().map(updateReservation, ReservationResponse.class));
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<ReservationResponse>> cursor(
            @RequestParam(name = "page", required = false,defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false,defaultValue = "10") int pageSize
    ){
        Page<Reservation> reservationPage = this.reservationService.cursor(page, pageSize);
        Page<ReservationResponse> reservationResponses = reservationPage.map(reservation -> this.modelMapperService.forResponse().map(reservation, ReservationResponse.class));
        return ResultHelper.cursor(reservationResponses);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){
        this.reservationService.delete(id);
        return ResultHelper.ok();
    }
}
