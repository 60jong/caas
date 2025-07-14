package dev._60jong.caas.hub.agent.exception;

import dev._60jong.caas.shared.exception.CaasException;

public class NotFoundByIdException extends CaasException {

    public NotFoundByIdException(String message) {
        super(message);
    }
}
