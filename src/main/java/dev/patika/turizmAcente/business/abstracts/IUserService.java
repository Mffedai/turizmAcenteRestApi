package dev.patika.turizmAcente.business.abstracts;


import dev.patika.turizmAcente.dto.request.user.UserSaveRequest;
import dev.patika.turizmAcente.dto.response.user.UserResponse;
import dev.patika.turizmAcente.entity.Users;

public interface IUserService extends IBaseService<Users, UserSaveRequest, UserResponse>{
    Users findByNameAndPassword(String name, String password);
}
