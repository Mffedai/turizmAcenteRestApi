package dev.patika.turizmAcente.api;

import dev.patika.turizmAcente.business.abstracts.IPensionService;
import dev.patika.turizmAcente.core.config.modelMapper.IModelMapperService;
import dev.patika.turizmAcente.core.result.Result;
import dev.patika.turizmAcente.core.result.ResultData;
import dev.patika.turizmAcente.core.utilies.ResultHelper;
import dev.patika.turizmAcente.dto.CursorResponse;
import dev.patika.turizmAcente.dto.request.pension.PensionSaveRequest;
import dev.patika.turizmAcente.dto.request.pension.PensionUpdateRequest;
import dev.patika.turizmAcente.dto.response.pension.PensionResponse;
import dev.patika.turizmAcente.entity.Pension;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/pensions")
@RequiredArgsConstructor
public class PensionController {
    private final IPensionService pensionService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<PensionResponse> save(@Valid @RequestBody PensionSaveRequest pensionSaveRequest){
        return this.pensionService.save(pensionSaveRequest);
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<PensionResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ){
        return this.pensionService.cursor(page, pageSize);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PensionResponse> update(@Valid @RequestBody PensionUpdateRequest pensionUpdateRequest){
        return this.pensionService.update(pensionUpdateRequest);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable Long id){
        this.pensionService.delete(id);
        return ResultHelper.ok();
    }
}
