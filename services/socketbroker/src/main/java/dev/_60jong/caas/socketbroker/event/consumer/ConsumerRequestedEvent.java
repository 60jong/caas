package dev._60jong.caas.socketbroker.event.consumer;

import java.net.Socket;

public record ConsumerRequestedEvent(
        Socket consumerSocket
) {
}
