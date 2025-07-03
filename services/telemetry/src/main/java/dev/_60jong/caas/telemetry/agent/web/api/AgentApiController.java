package dev._60jong.caas.telemetry.agent.web.api;

import dev._60jong.caas.telemetry.agent.domain.entity.Agent;
import dev._60jong.caas.telemetry.agent.repository.AgentRepository;
import dev._60jong.caas.telemetry.agent.web.request.AgentRegisterRequest;
import dev._60jong.caas.telemetry.common.IpEncryptor;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/agent")
public class AgentApiController {

    private final AgentRepository agentRepository;

    @PostMapping("/register")
    public String registerAgent(@RequestBody AgentRegisterRequest registerRequest,
                                HttpServletRequest httpRequest) {
        return agentRepository.save(new Agent(IpEncryptor.encrypt(registerRequest.ip().getIp()))).getId();
    }
}
