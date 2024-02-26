package dev.patika.turizmAcente.api;

import dev.patika.turizmAcente.business.abstracts.ISessionService;
import dev.patika.turizmAcente.core.config.modelMapper.IModelMapperService;
import dev.patika.turizmAcente.core.result.Result;
import dev.patika.turizmAcente.core.result.ResultData;
import dev.patika.turizmAcente.core.utilies.ResultHelper;
import dev.patika.turizmAcente.dto.CursorResponse;
import dev.patika.turizmAcente.dto.request.session.SessionSaveRequest;
import dev.patika.turizmAcente.dto.request.session.SessionUpdateRequest;
import dev.patika.turizmAcente.dto.response.session.SessionResponse;
import dev.patika.turizmAcente.entity.Session;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/sessions")
@RequiredArgsConstructor
public class SessionController {
    private final ISessionService sessionService;
    private final IModelMapperService modelMapperService;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<SessionResponse> save(@Valid @RequestBody SessionSaveRequest sessionSaveRequest){
        return this.sessionService.save(sessionSaveRequest);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<SessionResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize){
        Page<Session> sessionPage = this.sessionService.cursor(page, pageSize);
        Page<SessionResponse> sessionResponsePage = sessionPage.map(session -> this.modelMapperService.forResponse().map(session, SessionResponse.class));
        return ResultHelper.cursor(sessionResponsePage);
    }
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<SessionResponse> update(@Valid @RequestBody SessionUpdateRequest sessionUpdateRequest){
        this.sessionService.get(sessionUpdateRequest.getId());
        Session updateSession = this.modelMapperService.forRequest().map(sessionUpdateRequest, Session.class);
        this.sessionService.update(updateSession);
        return ResultHelper.success(this.modelMapperService.forResponse().map(updateSession, SessionResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable Long id){
        this.sessionService.delete(id);
        return ResultHelper.ok();
    }
}
