package dev._60jong.p2pcaas.hub.agent.web.request;

import dev._60jong.p2pcaas.shared.domain.IpAddr;

public record AgentRegisterRequest(
        String name,
        IpAddr ip
) {
}
