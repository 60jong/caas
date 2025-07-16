package dev._60jong.p2pcaas.hub.agent.exception;

import dev._60jong.p2pcaas.shared.exception.CaasException;

public class NotFoundByIdException extends CaasException {

    public NotFoundByIdException(String message) {
        super(message);
    }
}
