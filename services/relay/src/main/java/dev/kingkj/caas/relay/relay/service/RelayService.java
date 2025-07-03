package dev.kingkj.caas.relay.relay.service;

import dev.kingkj.caas.relay.connection.store.ConnectionStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.Socket;

@RequiredArgsConstructor
@Service
public class RelayService {

    private final ConnectionStore agentConnectionStore;
    private final ConnectionStore relayConnectionStore;

    /**
     * agent가 연결된 뒤, agentId를 대상으로 연결된 relay client 사이의
     * 통신을 중개한다.
     *
     * @param agentId
     */
    public void relay(final String agentId) {
        Socket clientSocket = relayConnectionStore.pollConnection(agentId);
        Socket agentSocket = agentConnectionStore.pollConnection(agentId);

        Thread.ofPlatform()
                .name("client->agent")
                .unstarted(() -> forwardData(clientSocket, agentSocket))
                .start();

        Thread.ofPlatform()
                .name("agent->client")
                .unstarted(() -> forwardData(agentSocket, clientSocket))
                .start();
    }

    // TODO : forward 역할 분리
    private void forwardData(Socket fromSock, Socket toSock) {
        byte[] buf = new byte[1024];
        int read;

        try {
            while ((read = fromSock.getInputStream().read(buf)) != -1) {
                toSock.getOutputStream().write(buf, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fromSock.close();
                toSock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
