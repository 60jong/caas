package dev._60jong.p2pcaas.socketbroker.event.conport.handler;

import dev._60jong.p2pcaas.shared.util.IpEncryptor;
import dev._60jong.p2pcaas.socketbroker.broker.SocketDataBroker;
import dev._60jong.p2pcaas.socketbroker.connection.store.ConnectionStore;
import dev._60jong.p2pcaas.socketbroker.event.conport.ConportConnectedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.net.Socket;

@RequiredArgsConstructor
@Component
public class ConportEventHandler {

    private final ConnectionStore conportConnectionStore;
    private final SocketDataBroker broker;

    @Async
    @EventListener
    public void onConportConnectedEvent(ConportConnectedEvent event) {
        Socket conportSocket = event.agentSocket();
        final String encryptedIp = IpEncryptor.encrypt(conportSocket.getInetAddress().getHostAddress());

        conportConnectionStore.saveConnection(encryptedIp, conportSocket);
        System.out.println("Connected to conport with ip : " + encryptedIp);
        broker.execute(encryptedIp);
    }
}
