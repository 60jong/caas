package dev.kingkj.caas.relay.event.agent;

import java.net.Socket;

public record AgentConnectedEvent(
        Socket agentSocket
) {
}
