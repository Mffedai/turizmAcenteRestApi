package dev.patika.turizmAcente.business.abstracts;


import dev.patika.turizmAcente.core.result.ResultData;
import dev.patika.turizmAcente.dto.request.user.UserSaveRequest;
import dev.patika.turizmAcente.dto.request.user.UserUpdateRequest;
import dev.patika.turizmAcente.dto.response.user.UserResponse;
import dev.patika.turizmAcente.entity.Users;

import java.util.List;

public interface IUserService extends IBaseService<Users, UserSaveRequest, UserUpdateRequest, UserResponse>{
    ResultData<List<UserResponse>> findByRole(String  role);
}
