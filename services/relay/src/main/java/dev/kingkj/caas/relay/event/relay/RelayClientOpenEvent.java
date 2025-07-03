package dev.kingkj.caas.relay.event.relay;

import java.net.Socket;

public record RelayClientOpenEvent(
        Socket clientSocket
) {
}
