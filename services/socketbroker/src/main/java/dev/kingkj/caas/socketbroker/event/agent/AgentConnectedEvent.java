package dev.kingkj.caas.socketbroker.event.agent;

import java.net.Socket;

public record AgentConnectedEvent(
        Socket agentSocket
) {
}
