package dev.kingkj.caas.socketbroker.consumer.server;

import dev.kingkj.caas.socketbroker.connection.store.ConnectionStore;
import dev.kingkj.caas.socketbroker.event.consumer.ConsumerRequestedEvent;
import dev.kingkj.caas.relay.util.IpEncryptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
@RequiredArgsConstructor
public class ConsumerServer {

    private final int port;
    private final ApplicationEventPublisher eventPublisher;

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            log.info("Consumer server started on port {}", port);

            while (true) {
                Socket consumerSocket = serverSocket.accept();

                eventPublisher.publishEvent(new ConsumerRequestedEvent(consumerSocket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
