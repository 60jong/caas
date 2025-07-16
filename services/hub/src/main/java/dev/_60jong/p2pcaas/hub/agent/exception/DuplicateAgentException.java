package dev._60jong.p2pcaas.hub.agent.exception;

import dev._60jong.p2pcaas.shared.exception.CaasException;

public class DuplicateAgentException extends CaasException {

    public DuplicateAgentException(String message) {
        super(message);
    }
}
