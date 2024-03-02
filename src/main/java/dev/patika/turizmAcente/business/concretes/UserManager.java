package dev.patika.turizmAcente.business.concretes;

import dev.patika.turizmAcente.business.abstracts.IUserService;
import dev.patika.turizmAcente.core.config.ConvertEntityToResponse;
import dev.patika.turizmAcente.core.config.modelMapper.IModelMapperService;
import dev.patika.turizmAcente.core.exception.DataAlreadyExistException;
import dev.patika.turizmAcente.core.exception.NotFoundException;
import dev.patika.turizmAcente.core.result.ResultData;
import dev.patika.turizmAcente.core.utilies.Msg;
import dev.patika.turizmAcente.core.utilies.ResultHelper;
import dev.patika.turizmAcente.dao.UserRepo;
import dev.patika.turizmAcente.dto.CursorResponse;
import dev.patika.turizmAcente.dto.request.user.UserSaveRequest;
import dev.patika.turizmAcente.dto.request.user.UserUpdateRequest;
import dev.patika.turizmAcente.dto.response.user.UserResponse;
import dev.patika.turizmAcente.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserManager implements IUserService {
    private final UserRepo userRepo;
    private final IModelMapperService modelMapperService;
    private final ConvertEntityToResponse<Users, UserResponse> convert;
    @Override
    public ResultData<UserResponse> save(UserSaveRequest userSaveRequest) {
        Users user = this.userRepo.findByName(userSaveRequest.getName());
        if (user != null){
            throw new DataAlreadyExistException(Msg.getEntityForMsg(Users.class));
        }
        Users saveUser = this.modelMapperService.forRequest().map(userSaveRequest, Users.class);
        saveUser.setPassword(BCrypt.hashpw(userSaveRequest.getPassword(), BCrypt.gensalt()));
        return ResultHelper.created(this.modelMapperService.forResponse().map(this.userRepo.save(saveUser), UserResponse.class));
    }
    @Override
    public Users get(Long id) {
        return this.userRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }
    @Override
    public ResultData<CursorResponse<UserResponse>> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Users> userPage = this.userRepo.findAll(pageable);
        Page<UserResponse> userResponsePage = userPage.map(user -> this.modelMapperService.forResponse().map(user, UserResponse.class));
        return ResultHelper.cursor(userResponsePage);
    }
    @Override
    public ResultData<UserResponse> update(UserUpdateRequest userUpdateRequest) {
        this.get(userUpdateRequest.getId());
        Users updateUser = this.modelMapperService.forRequest().map(userUpdateRequest, Users.class);
        return ResultHelper.success(this.modelMapperService.forResponse().map(this.userRepo.save(updateUser), UserResponse.class));
    }
    @Override
    public boolean delete(Long id) {
        Users user = this.get(id);
        this.userRepo.delete(user);
        return true;
    }
    @Override
    public ResultData<List<UserResponse>> findByRole(String role) {
        List<Users> usersList = this.userRepo.findByRole(Users.Role.valueOf(role));
        List<UserResponse> userResponseList = this.convert.convertToResponseList(usersList, UserResponse.class);
        return ResultHelper.success(userResponseList);
    }
}