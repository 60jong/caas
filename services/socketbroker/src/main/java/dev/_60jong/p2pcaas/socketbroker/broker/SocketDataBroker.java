package dev._60jong.p2pcaas.socketbroker.broker;


import dev._60jong.p2pcaas.socketbroker.connection.store.ConnectionStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

@Slf4j
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
            InputStream in = fromSock.getInputStream();
            OutputStream out = toSock.getOutputStream();

            while ((read = in.read(buf)) != -1) {
                out.write(buf, 0, read);
                out.flush();
            }
        } catch (SocketException se) {
            if (fromSock.isClosed() || toSock.isClosed()) {
                return;
            }
            log.error(se.getMessage(), se);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            closeSockets(fromSock, toSock);
        }
    }

    private void closeSockets(Socket fromSock, Socket toSock) {
        try {
            if (!fromSock.isClosed()) {
                fromSock.close();
            }
        } catch (IOException e) {
            log.error("Error closing fromSock: {}", e.getMessage(), e);
        }

        try {
            if (!toSock.isClosed()) {
                toSock.close();
            }
        } catch (IOException e) {
            log.error("Error closing toSock: {}", e.getMessage(), e);
        }
    }
}
