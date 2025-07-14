package dev._60jong.caas.hub.broker.exception;

import dev._60jong.caas.shared.exception.CaasException;

public class InvalidBrokerEndpointSequenceException extends CaasException {

    public InvalidBrokerEndpointSequenceException(String message) {
        super(message);
    }
}
