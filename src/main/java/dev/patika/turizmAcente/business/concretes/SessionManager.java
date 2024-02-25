package dev.patika.turizmAcente.business.concretes;

import dev.patika.turizmAcente.business.abstracts.ISessionService;
import dev.patika.turizmAcente.core.result.ResultData;
import dev.patika.turizmAcente.dto.request.session.SessionSaveRequest;
import dev.patika.turizmAcente.dto.response.session.SessionResponse;
import dev.patika.turizmAcente.entity.Session;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class SessionManager implements ISessionService {
    @Override
    public ResultData<SessionResponse> save(SessionSaveRequest sessionSaveRequest) {
        return null;
    }

    @Override
    public Session get(Long id) {
        return null;
    }

    @Override
    public Page<Session> cursor(int page, int pageSize) {
        return null;
    }

    @Override
    public Session update(Session entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
