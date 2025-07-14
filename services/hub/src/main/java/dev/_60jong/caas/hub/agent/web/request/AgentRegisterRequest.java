package dev._60jong.caas.hub.agent.web.request;

import dev._60jong.caas.shared.domain.IpAddr;

public record AgentRegisterRequest(
        String name,
        IpAddr ip
) {
}
