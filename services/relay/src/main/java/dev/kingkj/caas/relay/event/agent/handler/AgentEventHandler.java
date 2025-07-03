package dev.kingkj.caas.relay.event.agent.handler;

import dev.kingkj.caas.relay.connection.store.ConnectionStore;
import dev.kingkj.caas.relay.event.agent.AgentConnectedEvent;
import dev.kingkj.caas.relay.relay.service.RelayService;
import dev.kingkj.caas.relay.util.IpEncryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.net.Socket;

@RequiredArgsConstructor
@Component
public class AgentEventHandler {

    private final ConnectionStore agentConnectionStore;
    private final RelayService relayService;

    @Async
    @EventListener
    public void onAgentConnectedEvent(AgentConnectedEvent event) {
        Socket agentSocket = event.agentSocket();
        final String encryptedIp = IpEncryptor.encrypt(agentSocket.getInetAddress().getHostAddress());

        agentConnectionStore.saveConnection(encryptedIp, agentSocket);

        relayService.relay(encryptedIp);
    }
}
