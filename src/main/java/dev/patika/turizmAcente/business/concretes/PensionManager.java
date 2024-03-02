package dev.patika.turizmAcente.business.concretes;

import dev.patika.turizmAcente.business.abstracts.IPensionService;
import dev.patika.turizmAcente.core.config.modelMapper.IModelMapperService;
import dev.patika.turizmAcente.core.exception.NotFoundException;
import dev.patika.turizmAcente.core.result.ResultData;
import dev.patika.turizmAcente.core.utilies.Msg;
import dev.patika.turizmAcente.core.utilies.ResultHelper;
import dev.patika.turizmAcente.dao.PensionRepo;
import dev.patika.turizmAcente.dto.CursorResponse;
import dev.patika.turizmAcente.dto.request.pension.PensionSaveRequest;
import dev.patika.turizmAcente.dto.request.pension.PensionUpdateRequest;
import dev.patika.turizmAcente.dto.response.pension.PensionResponse;
import dev.patika.turizmAcente.entity.Pension;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PensionManager implements IPensionService {
    private final PensionRepo pensionRepo;
    private final IModelMapperService modelMapperService;

    @Override
    public ResultData<PensionResponse> save(PensionSaveRequest pensionSaveRequest) {
        Pension savePension = this.modelMapperService.forRequest().map(pensionSaveRequest, Pension.class);
        return ResultHelper.created(this.modelMapperService.forResponse().map(this.pensionRepo.save(savePension), PensionResponse.class));
    }

    @Override
    public Pension get(Long id) {
        return this.pensionRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public ResultData<CursorResponse<PensionResponse>> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Pension> pensionPage = this.pensionRepo.findAll(pageable);
        Page<PensionResponse> pensionResponses = pensionPage.map(pension -> this.modelMapperService.forResponse().map(pension, PensionResponse.class));
        return ResultHelper.cursor(pensionResponses);
    }

    @Override
    public ResultData<PensionResponse> update(PensionUpdateRequest pensionUpdateRequest) {
        this.get(pensionUpdateRequest.getId());
        Pension updatePension = this.modelMapperService.forRequest().map(pensionUpdateRequest, Pension.class);
        return ResultHelper.success(this.modelMapperService.forResponse().map(updatePension, PensionResponse.class));
    }

    @Override
    public boolean delete(Long id) {
        Pension pension = this.get(id);
        this.pensionRepo.delete(pension);
        return true;
    }
}
