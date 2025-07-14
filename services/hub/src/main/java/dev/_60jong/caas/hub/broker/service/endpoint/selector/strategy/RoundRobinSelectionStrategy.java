package dev._60jong.caas.hub.broker.service.endpoint.selector.strategy;

import dev._60jong.caas.hub.broker.domain.entity.BrokerEndpoint;

import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinSelectionStrategy implements BrokerEndpointSelectionStrategy {

    private AtomicInteger counter = new AtomicInteger(0);
    private final int totalEndpoints = BrokerEndpoint.values().length;

    @Override
    public BrokerEndpoint select() {
        return BrokerEndpoint.getBySequence(
                counter.getAndUpdate(c -> (c + 1) % totalEndpoints));
    }
}
