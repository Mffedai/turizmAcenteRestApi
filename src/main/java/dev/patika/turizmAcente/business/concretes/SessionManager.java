package dev.patika.turizmAcente.business.concretes;

import dev.patika.turizmAcente.business.abstracts.IHotelService;
import dev.patika.turizmAcente.business.abstracts.ISessionService;
import dev.patika.turizmAcente.core.config.modelMapper.IModelMapperService;
import dev.patika.turizmAcente.core.exception.DataAlreadyExistException;
import dev.patika.turizmAcente.core.exception.NotFoundException;
import dev.patika.turizmAcente.core.result.ResultData;
import dev.patika.turizmAcente.core.utilies.Msg;
import dev.patika.turizmAcente.core.utilies.ResultHelper;
import dev.patika.turizmAcente.dao.SessionRepo;
import dev.patika.turizmAcente.dto.CursorResponse;
import dev.patika.turizmAcente.dto.request.session.SessionSaveRequest;
import dev.patika.turizmAcente.dto.request.session.SessionUpdateRequest;
import dev.patika.turizmAcente.dto.response.session.SessionResponse;
import dev.patika.turizmAcente.entity.Hotel;
import dev.patika.turizmAcente.entity.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionManager implements ISessionService {
    private final IHotelService hotelService;
    private final SessionRepo sessionRepo;
    private final IModelMapperService modelMapperService;
    @Override
    public ResultData<SessionResponse> save(SessionSaveRequest sessionSaveRequest) {
        Hotel hotel = this.hotelService.get(Long.valueOf(sessionSaveRequest.getHotelId()));
        Optional<Session> sessionList = this.findByHotelAndStrtDateAndFnshDate(
                hotel,
                sessionSaveRequest.getStrtDate(),
                sessionSaveRequest.getFnshDate()
        );
        if (sessionList.isPresent()){
            throw new DataAlreadyExistException(Msg.getEntityForMsg(Session.class));
        }
        sessionSaveRequest.setHotelId(null);
        Session saveSession = this.modelMapperService.forRequest().map(sessionSaveRequest, Session.class);
        saveSession.setHotel(hotel);
        return ResultHelper.created(this.modelMapperService.forResponse().map(this.sessionRepo.save(saveSession), SessionResponse.class));
    }
    @Override
    public Session get(Long id) {
        return this.sessionRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }
    @Override
    public ResultData<CursorResponse<SessionResponse>> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Session> sessionPage = this.sessionRepo.findAll(pageable);
        Page<SessionResponse> sessionResponsePage = sessionPage.map(session -> this.modelMapperService.forResponse().map(session, SessionResponse.class));
        return ResultHelper.cursor(sessionResponsePage);
    }
    @Override
    public ResultData<SessionResponse> update(SessionUpdateRequest sessionUpdateRequest) {
        this.get(sessionUpdateRequest.getId());
        Session updateSession = this.modelMapperService.forRequest().map(sessionUpdateRequest, Session.class);
        return ResultHelper.success(this.modelMapperService.forResponse().map(this.sessionRepo.save(updateSession), SessionResponse.class));
    }
    @Override
    public boolean delete(Long id) {
        Session session = this.get(id);
        this.sessionRepo.delete(session);
        return true;
    }
    @Override
    public Optional<Session> findByHotelAndStrtDateAndFnshDate(Hotel hotel, LocalDate strt_date, LocalDate fnsh_date) {
        return this.sessionRepo.findByHotelAndStrtDateAndFnshDate(hotel, strt_date, fnsh_date);
    }
}