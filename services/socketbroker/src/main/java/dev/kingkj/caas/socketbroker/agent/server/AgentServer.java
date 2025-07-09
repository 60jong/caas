package dev.kingkj.caas.socketbroker.agent.server;

import dev.kingkj.caas.socketbroker.event.agent.AgentConnectedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
@RequiredArgsConstructor
public class AgentServer {

    private final int port;
    private final ApplicationEventPublisher eventPublisher;

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            log.info("Agent server started on port {}", port);

            while (true) {
                Socket agentSocket = serverSocket.accept();

                eventPublisher.publishEvent(new AgentConnectedEvent(agentSocket));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
