package dev._60jong.p2pcaas.socketbroker.event.conport;

import java.net.Socket;

public record ConportConnectedEvent(
        Socket agentSocket
) {
}
