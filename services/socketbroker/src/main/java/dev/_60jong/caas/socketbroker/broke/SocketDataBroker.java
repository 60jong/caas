package dev._60jong.caas.socketbroker.broke;


import dev._60jong.caas.socketbroker.connection.store.ConnectionStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

@RequiredArgsConstructor
@Service
public class SocketDataBroker {

    private final ConnectionStore conportConnectionStore;
    private final ConnectionStore consumerConnectionStore;

    /**
     * agent가 연결된 뒤, conportId를 대상으로 연결된 relay client 사이의
     * 통신을 중개한다.
     *
     * @param conportId
     */
    public void execute(final String conportId) {
        Socket consumerSock = conportConnectionStore.pollConnection(conportId);
        Socket conportSock = consumerConnectionStore.pollConnection(conportId);

        Thread.ofVirtual()
                .start(() -> forwardData(conportSock, consumerSock));
        Thread.ofVirtual()
                .start(() -> forwardData(consumerSock, conportSock));
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
                if (!fromSock.isClosed()) {
                    fromSock.close();
                }

                if (!toSock.isClosed()) {
                    toSock.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
