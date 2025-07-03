package dev.kingkj.caas.relay.relay.server;

import dev.kingkj.caas.relay.connection.store.ConnectionStore;
import dev.kingkj.caas.relay.event.relay.RelayClientOpenEvent;
import dev.kingkj.caas.relay.util.IpEncryptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
@RequiredArgsConstructor
public class RelayServer {

    private final int port;
    private final ApplicationEventPublisher eventPublisher;

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            log.info("Relay server started on port {}", port);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                eventPublisher.publishEvent(new RelayClientOpenEvent(clientSocket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
