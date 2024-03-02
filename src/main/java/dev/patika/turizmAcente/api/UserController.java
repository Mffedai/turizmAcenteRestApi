package dev.patika.turizmAcente.api;

import dev.patika.turizmAcente.business.abstracts.IUserService;
import dev.patika.turizmAcente.core.config.modelMapper.IModelMapperService;
import dev.patika.turizmAcente.core.result.Result;
import dev.patika.turizmAcente.core.result.ResultData;
import dev.patika.turizmAcente.core.utilies.ResultHelper;
import dev.patika.turizmAcente.dto.CursorResponse;
import dev.patika.turizmAcente.dto.request.user.UserSaveRequest;
import dev.patika.turizmAcente.dto.request.user.UserUpdateRequest;
import dev.patika.turizmAcente.dto.response.user.UserResponse;
import dev.patika.turizmAcente.entity.Users;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/admins")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<UserResponse> save(@Valid @RequestBody UserSaveRequest userSaveRequest){
        return this.userService.save(userSaveRequest);
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<UserResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ){
        return this.userService.cursor(page, pageSize);
    }
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<UserResponse> update(@Valid @RequestBody UserUpdateRequest userUpdateRequest){
        return this.userService.update(userUpdateRequest);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){
        this.userService.delete(id);
        return ResultHelper.ok();
    }
    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<UserResponse>> findRole(@PathVariable("name") String name){
        return this.userService.findByRole(name);
    }
}