package dev.patika.turizmAcente.business.abstracts;

import dev.patika.turizmAcente.dto.request.session.SessionSaveRequest;
import dev.patika.turizmAcente.dto.response.session.SessionResponse;
import dev.patika.turizmAcente.entity.Session;

public interface ISessionService extends IBaseService<Session, SessionSaveRequest, SessionResponse> {
}
