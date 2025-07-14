package dev._60jong.caas.hub.agent.exception;

import dev._60jong.caas.shared.exception.CaasException;

public class DuplicateAgentException extends CaasException {

    public DuplicateAgentException(String message) {
        super(message);
    }
}
