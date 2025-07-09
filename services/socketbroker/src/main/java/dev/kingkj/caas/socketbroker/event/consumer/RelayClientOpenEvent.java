package dev.kingkj.caas.socketbroker.event.consumer;

import java.net.Socket;

public record ConsumerRequestedEvent(
        Socket consumerSocket
) {
}
