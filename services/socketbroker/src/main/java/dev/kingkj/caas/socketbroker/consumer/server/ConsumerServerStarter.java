package dev.kingkj.caas.socketbroker.consumer.server;

import dev.kingkj.caas.socketbroker.connection.store.ConnectionStore;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

// TODO : Relay 서버 다운시에 재가동 로직 구현
@Slf4j
@Component
public class ConsumerServerStarter {

    private final int port;
    private final ApplicationEventPublisher eventPublisher;

    public RelayServerStarter(
            @Value("${caas.socketbroker.server.port}") int port,
            ApplicationEventPublisher eventPublisher
    ) {
        this.port = port;
        this.eventPublisher = eventPublisher;
    }

    @PostConstruct
    public void startServer() {
        Thread.ofPlatform()
                .name("consumer-server-t")
                .unstarted(() -> new RelayServer(port, eventPublisher).run())
                .start();
    }
}
