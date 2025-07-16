package dev._60jong.p2pcaas.hub.agent.web.api;

import dev._60jong.p2pcaas.hub.agent.domain.entity.Agent;
import dev._60jong.p2pcaas.hub.agent.domain.entity.Conport;
import dev._60jong.p2pcaas.hub.agent.exception.NotFoundByIdException;
import dev._60jong.p2pcaas.hub.agent.repository.AgentRepository;
import dev._60jong.p2pcaas.hub.agent.repository.ConportRepository;
import dev._60jong.p2pcaas.hub.agent.web.request.ConportRegisterRequest;
import dev._60jong.p2pcaas.hub.agent.web.response.ConportRegisterResponse;
import dev._60jong.p2pcaas.shared.web.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/agent")
public class ConportApiController {

    private final AgentRepository agentRepository;
    private final ConportRepository conportRepository;

    @PostMapping("/{agentId}/conport")
    public ApiResponse<ConportRegisterResponse> registerConport(
            @PathVariable("agentId") String agentId,
            @RequestBody ConportRegisterRequest request
    ) {
        Agent agent = agentRepository.findById(agentId)
                                     .orElseThrow(() -> new NotFoundByIdException("Cannot found Agent"));

        Conport conport = new Conport(agent);
        conportRepository.save(conport);

        return ApiResponse.success(new ConportRegisterResponse(conport.getId()));

    }
}
