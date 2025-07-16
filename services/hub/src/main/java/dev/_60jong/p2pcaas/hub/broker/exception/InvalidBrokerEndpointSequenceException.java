package dev._60jong.p2pcaas.hub.broker.exception;

import dev._60jong.p2pcaas.shared.exception.CaasException;

public class InvalidBrokerEndpointSequenceException extends CaasException {

    public InvalidBrokerEndpointSequenceException(String message) {
        super(message);
    }
}
