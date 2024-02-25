package dev.patika.turizmAcente.business.abstracts;

import dev.patika.turizmAcente.core.BaseEntity;
import dev.patika.turizmAcente.core.result.ResultData;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBaseService <E extends BaseEntity, REQUEST, RESPONSE> {
    //List<RESPONSE> findAll();
    ResultData<RESPONSE> save(REQUEST request);
    E get(Long id);
    Page<E> cursor(int page, int pageSize);
    E update(E entity);
    boolean delete(Long id);
}
