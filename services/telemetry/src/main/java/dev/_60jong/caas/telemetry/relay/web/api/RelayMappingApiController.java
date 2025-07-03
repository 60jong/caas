package dev._60jong.caas.telemetry.relay.web.api;

import dev._60jong.caas.telemetry.agent.domain.entity.Agent;
import dev._60jong.caas.telemetry.agent.repository.AgentRepository;
import dev._60jong.caas.telemetry.common.IpEncryptor;
import dev._60jong.caas.telemetry.relay.domain.entity.RelayMapping;
import dev._60jong.caas.telemetry.relay.repository.RelayMappingRepository;
import dev._60jong.caas.telemetry.relay.web.request.RelayMappingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/relay/mapping")
public class RelayMappingApiController {

    private final AgentRepository agentRepository;
    private final RelayMappingRepository relayMappingRepository;

    @GetMapping("")
    public String getMappedAgentId(@RequestParam(name = "client_ip") String clientIp) {
        return relayMappingRepository.findAgentIdByClientIp(clientIp)   ;
    }

    @PostMapping("/register")
    public void registerMapping(@RequestBody RelayMappingRequest request) {
        Agent agent = agentRepository.findById(request.agentId()).get();

        relayMappingRepository.save(new RelayMapping(agent, IpEncryptor.encrypt(request.clientIp())));
    }
}
