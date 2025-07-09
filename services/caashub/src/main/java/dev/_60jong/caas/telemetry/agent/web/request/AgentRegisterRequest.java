package dev._60jong.caas.telemetry.agent.web.request;

import dev._60jong.caas.telemetry.common.IpAddr;

public record AgentRegisterRequest(
    IpAddr ip
) {
}
