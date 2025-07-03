package dev.kingkj.caas.relay.relay.server;

import dev.kingkj.caas.relay.connection.store.ConnectionStore;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

// TODO : Relay 서버 다운시에 재가동 로직 구현
@Slf4j
@Component
public class RelayServerStarter {

    private final int port;
    private final ApplicationEventPublisher eventPublisher;

    public RelayServerStarter(
            @Value("${caas.relay.server.port}") int port,
            ApplicationEventPublisher eventPublisher
    ) {
        this.port = port;
        this.eventPublisher = eventPublisher;
    }

    @PostConstruct
    public void startServer() {
        Thread.ofPlatform()
                .name("relay-server-t")
                .unstarted(() -> new RelayServer(port, eventPublisher).run())
                .start();
    }
}
