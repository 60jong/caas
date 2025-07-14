package dev._60jong.caas.socketbroker.server.consumer.server;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

// TODO : Relay 서버 다운시에 재가동 로직 구현
@Slf4j
@Component
public class ConsumerServerStarter {

    private final int port;
    private final String endpoint;
    private final ApplicationEventPublisher eventPublisher;

    public ConsumerServerStarter(
            @Value("${caas.consumer.server.port}") int port,
            @Value("${caas.endpoint}") String endpoint,
            ApplicationEventPublisher eventPublisher
    ) {
        this.port = port;
        this.endpoint = endpoint;
        this.eventPublisher = eventPublisher;
    }

    @PostConstruct
    public void startServer() {
        Thread.ofPlatform()
                .name("consumer-serv-t")
                .unstarted(() -> new ConsumerServer(port, eventPublisher).run())
                .start();
    }
}
