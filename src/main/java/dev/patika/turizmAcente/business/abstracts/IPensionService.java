package dev.patika.turizmAcente.business.abstracts;

import dev.patika.turizmAcente.dto.request.pension.PensionSaveRequest;
import dev.patika.turizmAcente.dto.request.pension.PensionUpdateRequest;
import dev.patika.turizmAcente.dto.response.pension.PensionResponse;
import dev.patika.turizmAcente.entity.Pension;

public interface IPensionService extends IBaseService<Pension, PensionSaveRequest, PensionUpdateRequest, PensionResponse> {
}
