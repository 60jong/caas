package dev._60jong.caas.hub.broker.service.endpoint.selector.strategy;

import dev._60jong.caas.hub.broker.domain.entity.BrokerEndpoint;

public interface BrokerEndpointSelectionStrategy {
    BrokerEndpoint select();
}
