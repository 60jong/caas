package dev._60jong.caas.socketbroker.event.conport;

import java.net.Socket;

public record ConportConnectedEvent(
        Socket agentSocket
) {
}
