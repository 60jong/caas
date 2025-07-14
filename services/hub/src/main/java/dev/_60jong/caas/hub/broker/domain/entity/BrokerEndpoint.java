package dev._60jong.caas.hub.broker.domain.entity;

import dev._60jong.caas.hub.broker.exception.InvalidBrokerEndpointSequenceException;
import dev._60jong.caas.shared.exception.CaasException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum BrokerEndpoint {
    MESSI(0),
    RONALDO(1),
    SON(2),
    INIESTA(3),
    XAVI(4),
    BUSQUETS(5),
    RAMOS(6),
    VANDIJK(7),
    ALVES(8),
    MARCELO(9),
    BUFFON(10),;


    private int sequence;

    BrokerEndpoint(int sequence) {
        this.sequence = sequence;
    }

    public static BrokerEndpoint getBySequence(int sequence) {
        return Arrays.stream(BrokerEndpoint.values())
                .filter(e -> e.sequence == sequence)
                .findAny()
                .orElseThrow(() -> new CaasException("Invalid broker endpoint sequence: " + sequence));
    }

    public static BrokerEndpoint getByName(String endpointName) {
        return Arrays.stream(BrokerEndpoint.values())
                .filter(e -> e.name().equals(endpointName.toUpperCase()))
                .findAny()
                .orElseThrow(() -> new CaasException("Invalid broker endpoint name: " + endpointName));
    }
}
