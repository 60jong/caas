package dev._60jong.caas.socketbroker.server.conport.server;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

// TODO : Agent 서버 다운시에 재가동 로직 구현
@Slf4j
@Component
public class ConportServerStarter {

    private final int port;
    private final ApplicationEventPublisher eventPublisher;

    public ConportServerStarter(
            @Value("${caas.conport.server.port}") int port,
            ApplicationEventPublisher eventPublisher
    ) {
        this.port = port;
        this.eventPublisher = eventPublisher;
    }

    @PostConstruct
    public void startServer() {
        Thread.ofPlatform()
                .name("conport-serv-t")
                .unstarted(() -> new ConportServer(port, eventPublisher).run())
                .start();
    }
}
