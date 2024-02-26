package dev.patika.turizmAcente.api;

import dev.patika.turizmAcente.business.abstracts.IRoomService;
import dev.patika.turizmAcente.core.config.modelMapper.IModelMapperService;
import dev.patika.turizmAcente.core.result.Result;
import dev.patika.turizmAcente.core.result.ResultData;
import dev.patika.turizmAcente.core.utilies.ResultHelper;
import dev.patika.turizmAcente.dto.CursorResponse;
import dev.patika.turizmAcente.dto.request.room.RoomSaveRequest;
import dev.patika.turizmAcente.dto.request.room.RoomUpdateRequest;
import dev.patika.turizmAcente.dto.response.room.RoomResponse;
import dev.patika.turizmAcente.entity.Room;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final IRoomService roomService;
    private final IModelMapperService modelMapperService;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<RoomResponse> save(@Valid @RequestBody RoomSaveRequest roomSaveRequest){
        return this.roomService.save(roomSaveRequest);
    }
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<RoomResponse> update(@Valid @RequestBody RoomUpdateRequest roomUpdateRequest){
        this.roomService.get(roomUpdateRequest.getId());
        Room updateRoom = this.modelMapperService.forRequest().map(roomUpdateRequest, Room.class);
        this.roomService.update(updateRoom);
        return ResultHelper.success(this.modelMapperService.forResponse().map(updateRoom, RoomResponse.class));
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<RoomResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ){
        Page<Room> roomPage = this.roomService.cursor(page, pageSize);
        Page<RoomResponse> roomResponsePage = roomPage.map(room -> this.modelMapperService.forResponse().map(room, RoomResponse.class));
        return ResultHelper.cursor(roomResponsePage);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){
        this.roomService.delete(id);
        return ResultHelper.ok();
    }
}
