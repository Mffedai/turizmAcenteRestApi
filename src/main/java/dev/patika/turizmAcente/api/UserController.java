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


@RestController
@RequestMapping("/v1/admins")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    private final IModelMapperService modelMapperService;

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
        Page<Users> userPage =this.userService.cursor(page, pageSize);
        Page<UserResponse> userResponsePage = userPage.map(user -> this.modelMapperService.forResponse().map(user, UserResponse.class));
        return ResultHelper.cursor(userResponsePage);
    }
    @PutMapping()
    public ResultData<UserResponse> get(@Valid @RequestBody UserUpdateRequest userUpdateRequest){
        this.userService.get(userUpdateRequest.getId());
        Users updateUser = this.modelMapperService.forRequest().map(userUpdateRequest, Users.class);
        this.userService.update(updateUser);
        return ResultHelper.success(this.modelMapperService.forResponse().map(updateUser, UserResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){
        this.userService.delete(id);
        return ResultHelper.ok();
    }
}