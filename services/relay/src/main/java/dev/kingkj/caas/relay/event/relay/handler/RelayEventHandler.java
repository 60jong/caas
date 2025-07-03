package dev.kingkj.caas.relay.event.relay.handler;

import dev.kingkj.caas.relay.connection.store.ConnectionStore;
import dev.kingkj.caas.relay.event.relay.RelayClientOpenEvent;
import dev.kingkj.caas.relay.util.IpEncryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.net.Socket;

@RequiredArgsConstructor
@Component
public class RelayEventHandler {

    private final RedisTemplate<String, String> redisTemplate; // for agent
    private final ConnectionStore relayConnectionStore;

    @Async
    @EventListener
    public void onRelayClientOpenEvent(RelayClientOpenEvent event) {
        Socket clientSocket = event.clientSocket();
        final String clientId = IpEncryptor.encrypt(clientSocket.getInetAddress().getHostAddress());

        // TODO : find agentId mapped by clientId
        final String agentId = "TEST-AGENT";
        relayConnectionStore.saveConnection(agentId, clientSocket);
        // 고가용성을 위해 agentId가 처리할 클라이언트 커넥션 저장
        // agent는 그저 저장된 클라이언트 소켓 순서대로 통신

        redisTemplate.opsForList().rightPush(agentId, clientId);
    }
}
