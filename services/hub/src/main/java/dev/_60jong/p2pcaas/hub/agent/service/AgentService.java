package dev._60jong.p2pcaas.hub.agent.service;

import dev._60jong.p2pcaas.hub.agent.domain.entity.Agent;
import dev._60jong.p2pcaas.hub.agent.exception.DuplicateAgentException;
import dev._60jong.p2pcaas.hub.agent.repository.AgentRepository;
import dev._60jong.p2pcaas.hub.agent.web.response.AgentRegisterResponse;
import dev._60jong.p2pcaas.shared.domain.IpAddr;
import dev._60jong.p2pcaas.shared.util.IpEncryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AgentService {

    private final AgentRepository agentRepository;

    public AgentRegisterResponse registerAgent(final String agentName, final IpAddr agentIp) {
        final String agentId = IpEncryptor.encrypt(agentIp.getIp());

        // check agent ip existence ///
        if (agentRepository.existsById(agentId)) {
            throw new DuplicateAgentException("Already registered agent.");
        }

        Agent agent = new Agent(agentName, agentId);
        agentRepository.save(agent);

        return new AgentRegisterResponse(agent.getId());
    }
}
