package dev.patika.turizmAcente.business.concretes;

import dev.patika.turizmAcente.business.abstracts.IGuestService;
import dev.patika.turizmAcente.core.config.modelMapper.IModelMapperService;
import dev.patika.turizmAcente.core.exception.DataAlreadyExistException;
import dev.patika.turizmAcente.core.exception.NotFoundException;
import dev.patika.turizmAcente.core.result.ResultData;
import dev.patika.turizmAcente.core.utilies.Msg;
import dev.patika.turizmAcente.core.utilies.ResultHelper;
import dev.patika.turizmAcente.dao.GuestRepo;
import dev.patika.turizmAcente.dto.CursorResponse;
import dev.patika.turizmAcente.dto.request.guest.GuestSaveRequest;
import dev.patika.turizmAcente.dto.request.guest.GuestUpdateRequest;
import dev.patika.turizmAcente.dto.response.guest.GuestResponse;
import dev.patika.turizmAcente.entity.Guest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GuestManager implements IGuestService {
    private final GuestRepo guestRepo;
    private final IModelMapperService modelMapperService;
    @Override
    public ResultData<GuestResponse> save(GuestSaveRequest guestSaveRequest) {
        Optional<Guest> optionalGuest = this.findByMailAndTc(
                guestSaveRequest.getMail(),
                guestSaveRequest.getTc()
        );
        if (optionalGuest.isPresent()){
            throw new DataAlreadyExistException(Msg.getEntityForMsg(Guest.class));
        }
        Guest saveGuest = this.modelMapperService.forRequest().map(guestSaveRequest, Guest.class);
        return ResultHelper.created(this.modelMapperService.forResponse().map(this.guestRepo.save(saveGuest), GuestResponse.class));
    }
    @Override
    public Guest get(Long id) {
        return this.guestRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }
    @Override
    public ResultData<CursorResponse<GuestResponse>> cursor(int page, int pageSize) {
        return null;
    }
    @Override
    public ResultData<GuestResponse> update(GuestUpdateRequest guestUpdateRequest) {
        this.get(guestUpdateRequest.getId());
        Guest updateGuest = this.modelMapperService.forRequest().map(guestUpdateRequest, Guest.class);
        return ResultHelper.success(this.modelMapperService.forResponse().map(this.guestRepo.save(updateGuest), GuestResponse.class));
    }
    @Override
    public boolean delete(Long id) {
        Guest guest = this.get(id);
        this.guestRepo.delete(guest);
        return true;
    }

    @Override
    public Optional<Guest> findByMailAndTc(String mail, String tc) {
        return this.guestRepo.findByMailAndTc(mail, tc);
    }
}
