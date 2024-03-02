package dev.patika.turizmAcente.api;

import dev.patika.turizmAcente.business.abstracts.IGuestService;
import dev.patika.turizmAcente.core.config.modelMapper.IModelMapperService;
import dev.patika.turizmAcente.core.result.Result;
import dev.patika.turizmAcente.core.result.ResultData;
import dev.patika.turizmAcente.core.utilies.ResultHelper;
import dev.patika.turizmAcente.dto.request.guest.GuestSaveRequest;
import dev.patika.turizmAcente.dto.request.guest.GuestUpdateRequest;
import dev.patika.turizmAcente.dto.response.guest.GuestResponse;
import dev.patika.turizmAcente.entity.Guest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/guests")
@RequiredArgsConstructor
public class GuestController {
    private final IGuestService guestService;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<GuestResponse> save(@Valid @RequestBody GuestSaveRequest guestSaveRequest){
        return this.guestService.save(guestSaveRequest);
    }
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<GuestResponse> update(@Valid @RequestBody GuestUpdateRequest guestUpdateRequest){
        return this.guestService.update(guestUpdateRequest);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){
        this.guestService.delete(id);
        return ResultHelper.ok();
    }
}
