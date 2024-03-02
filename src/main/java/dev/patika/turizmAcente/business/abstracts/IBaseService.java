package dev.patika.turizmAcente.business.abstracts;

import dev.patika.turizmAcente.dto.CursorResponse;
import dev.patika.turizmAcente.dto.response.reservation.ReservationResponse;
import dev.patika.turizmAcente.entity.BaseEntity;
import dev.patika.turizmAcente.core.result.ResultData;
import org.springframework.data.domain.Page;

public interface IBaseService <E extends BaseEntity, SAVEREQUEST, UPDATEREQUEST, RESPONSE> {
    //List<RESPONSE> findAll();
    ResultData<RESPONSE> save(SAVEREQUEST request);
    E get(Long id);
    ResultData<CursorResponse<RESPONSE>> cursor(int page, int pageSize);
    ResultData<RESPONSE> update(UPDATEREQUEST request);
    boolean delete(Long id);
}
