package dev.kingkj.caas.socketbroker.event.consumer.handler;

import dev.kingkj.caas.socketbroker.connection.store.ConnectionStore;
import dev.kingkj.caas.socketbroker.event.consumer.ConsumerRequestedEvent;
import dev.kingkj.caas.socketbroker.util.IpEncryptor;
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
    private final ConnectionStore consumerConnectionStore;

    @Async
    @EventListener
    public void onRelayClientOpenEvent(ConsumerRequestedEvent event) {
        Socket consumerSocket = event.consumerSocket();
        final String consumerId = IpEncryptor.encrypt(consumerSocket.getInetAddress().getHostAddress());

        // TODO : find agentId mapped by consumerId
        final String agentId = "TEST-AGENT";
        consumerConnectionStore.saveConnection(agentId, consumerSocket);
        // 고가용성을 위해 agentId가 처리할 클라이언트 커넥션 저장
        // agent는 그저 저장된 클라이언트 소켓 순서대로 통신

        redisTemplate.opsForList().rightPush(agentId, consumerId);
    }
}
