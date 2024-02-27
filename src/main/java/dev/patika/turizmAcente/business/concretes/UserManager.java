package dev.patika.turizmAcente.business.concretes;

import dev.patika.turizmAcente.business.abstracts.IUserService;
import dev.patika.turizmAcente.core.config.modelMapper.IModelMapperService;
import dev.patika.turizmAcente.core.exception.DataAlreadyExistException;
import dev.patika.turizmAcente.core.exception.NotFoundException;
import dev.patika.turizmAcente.core.result.ResultData;
import dev.patika.turizmAcente.core.utilies.Msg;
import dev.patika.turizmAcente.core.utilies.ResultHelper;
import dev.patika.turizmAcente.dao.UserRepo;
import dev.patika.turizmAcente.dto.request.user.UserSaveRequest;
import dev.patika.turizmAcente.dto.response.user.UserResponse;
import dev.patika.turizmAcente.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManager implements IUserService {
    private final UserRepo userRepo;
    private final IModelMapperService modelMapperService;


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
    public Page<Users> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.userRepo.findAll(pageable);
    }

    @Override
    public Users update(Users user) {
        this.get(user.getId());
        return this.userRepo.save(user);
    }

    @Override
    public boolean delete(Long id) {
        Users user = this.get(id);
        this.userRepo.delete(user);
        return true;
    }

    @Override
    public Users findByNameAndPassword(String name, String password) {
        return this.userRepo.findByNameAndPassword(name, password);
    }

}
