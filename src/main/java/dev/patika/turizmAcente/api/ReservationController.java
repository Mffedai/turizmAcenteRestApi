package dev.patika.turizmAcente.api;

import dev.patika.turizmAcente.business.abstracts.IReservationService;
import dev.patika.turizmAcente.core.result.Result;
import dev.patika.turizmAcente.core.result.ResultData;
import dev.patika.turizmAcente.core.utilies.ResultHelper;
import dev.patika.turizmAcente.dto.CursorResponse;
import dev.patika.turizmAcente.dto.request.reservation.ReservationSaveRequest;
import dev.patika.turizmAcente.dto.request.reservation.ReservationUpdateRequest;
import dev.patika.turizmAcente.dto.response.reservation.ReservationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final IReservationService reservationService;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<ReservationResponse> save(@Valid @RequestBody ReservationSaveRequest reservationSaveRequest){
        return this.reservationService.save(reservationSaveRequest);
    }
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<ReservationResponse> update(@Valid @RequestBody ReservationUpdateRequest reservationUpdateRequest){
        return this.reservationService.update(reservationUpdateRequest);
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<ReservationResponse>> cursor(
            @RequestParam(name = "page", required = false,defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false,defaultValue = "10") int pageSize
    ){
        return this.reservationService.cursor(page, pageSize);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){
        this.reservationService.delete(id);
        return ResultHelper.ok();
    }
}
