package dev._60jong.p2pcaas.socketbroker.event.consumer.handler;

import dev._60jong.p2pcaas.shared.exception.CaasException;
import dev._60jong.p2pcaas.shared.util.IpEncryptor;

import dev._60jong.p2pcaas.shared.web.response.ApiResponse;
import dev._60jong.p2pcaas.socketbroker.common.feign.client.HubClient;
import dev._60jong.p2pcaas.socketbroker.connection.store.ConnectionStore;
import dev._60jong.p2pcaas.socketbroker.event.consumer.ConsumerRequestedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.net.Socket;

@Component
public class ConsumerEventHandler {

    private final RedisTemplate<String, String> redisTemplate; // for agent
    private final HubClient hubClient;
    private final String endpoint;
    private final ConnectionStore consumerConnectionStore;

    public ConsumerEventHandler(
            RedisTemplate<String, String> redisTemplate,
            HubClient hubClient,
            @Value("${caas.endpoint}") String endpoint,
            ConnectionStore consumerConnectionStore
    ) {
        this.redisTemplate = redisTemplate;
        this.hubClient = hubClient;
        this.endpoint = endpoint;
        this.consumerConnectionStore = consumerConnectionStore;
    }

    @Async
    @EventListener
    public void onRelayClientOpenEvent(ConsumerRequestedEvent event) {
        Socket consumerSocket = event.consumerSocket();
        final String consumerId = IpEncryptor.encrypt(consumerSocket.getInetAddress().getHostAddress());

        final String conportId = getConportIdByConsumerId(consumerId);
        System.out.println("ConsumerId : " + consumerId + " ConportId : " + conportId);
        consumerConnectionStore.saveConnection(conportId, consumerSocket);
        // 고가용성을 위해 conportId가 처리할 클라이언트 커넥션 저장
        // conport는 그저 저장된 클라이언트 소켓 순서대로 통신

        redisTemplate.opsForList().rightPush(conportId, consumerId);
    }

    private String getConportIdByConsumerId(String consumerId) {
        ApiResponse<String> apiResponse = hubClient.getConportId(this.endpoint, consumerId);
        if (apiResponse.getStatusCode() != 200) {
            throw new CaasException(apiResponse.getMessage());
        }
        return apiResponse.getData();
    }
}
