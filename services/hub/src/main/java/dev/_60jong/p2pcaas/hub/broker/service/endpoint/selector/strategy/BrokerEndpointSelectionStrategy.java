package dev._60jong.p2pcaas.hub.broker.service.endpoint.selector.strategy;

import dev._60jong.p2pcaas.hub.broker.domain.entity.BrokerEndpoint;

public interface BrokerEndpointSelectionStrategy {
    BrokerEndpoint select();
}
