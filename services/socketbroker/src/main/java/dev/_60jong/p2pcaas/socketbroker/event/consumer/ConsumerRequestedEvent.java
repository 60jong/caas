package dev._60jong.p2pcaas.socketbroker.event.consumer;

import java.net.Socket;

public record ConsumerRequestedEvent(
        Socket consumerSocket
) {
}
