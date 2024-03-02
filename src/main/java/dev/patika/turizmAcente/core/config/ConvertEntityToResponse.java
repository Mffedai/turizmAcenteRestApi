package dev.patika.turizmAcente.core.config;

import dev.patika.turizmAcente.core.config.modelMapper.IModelMapperService;
import dev.patika.turizmAcente.core.exception.NotFoundException;
import dev.patika.turizmAcente.core.utilies.Msg;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Configuration
public class ConvertEntityToResponse<E, RESPONSE> {
    private final IModelMapperService modelMapperService;
    public List<RESPONSE> convertToResponseList(List<E> entityList, Class<RESPONSE> responseClass) {
        if (entityList.isEmpty()){
            throw new NotFoundException(Msg.NOT_FOUND);
        } else {
            return entityList.stream()
                    .map(entity -> modelMapperService.forResponse().map(entity, responseClass))
                    .collect(Collectors.toList());
        }
    }
}