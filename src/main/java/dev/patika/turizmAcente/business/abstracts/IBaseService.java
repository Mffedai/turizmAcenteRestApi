package dev.patika.turizmAcente.business.abstracts;

import dev.patika.turizmAcente.entity.BaseEntity;
import dev.patika.turizmAcente.core.result.ResultData;
import org.springframework.data.domain.Page;

public interface IBaseService <E extends BaseEntity, REQUEST, RESPONSE> {
    //List<RESPONSE> findAll();
    ResultData<RESPONSE> save(REQUEST request);
    E get(Long id);
    Page<E> cursor(int page, int pageSize);
    E update(E entity);
    boolean delete(Long id);
}
