package dev._60jong.p2pcaas.socketbroker.server.conport.server;

import dev._60jong.p2pcaas.socketbroker.event.conport.ConportConnectedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
@RequiredArgsConstructor
public class ConportServer {

    private final int port;
    private final ApplicationEventPublisher eventPublisher;

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            log.info("Conport server started on port {}", port);

            while (true) {
                Socket agentSocket = serverSocket.accept();

                eventPublisher.publishEvent(new ConportConnectedEvent(agentSocket));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
