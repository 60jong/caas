package dev._60jong.p2pcaas.hub.agent.web.api;

import dev._60jong.p2pcaas.hub.agent.service.AgentService;
import dev._60jong.p2pcaas.hub.agent.web.request.AgentRegisterRequest;
import dev._60jong.p2pcaas.hub.agent.web.response.AgentRegisterResponse;
import dev._60jong.p2pcaas.shared.web.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/agent")
public class AgentApiController {

    private final AgentService agentService;

    @PostMapping("")
    public ApiResponse<AgentRegisterResponse> registerAgent(@RequestBody AgentRegisterRequest registerRequest) {
       return ApiResponse.success(agentService.registerAgent(registerRequest.name(),
                                                             registerRequest.ip()));
    }
}
