package dev._60jong.p2pcaas.hub.broker.service.endpoint.selector;

import dev._60jong.p2pcaas.hub.broker.domain.entity.BrokerEndpoint;
import dev._60jong.p2pcaas.hub.broker.service.endpoint.selector.strategy.BrokerEndpointSelectionStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BrokerEndpointSelector {

    private final BrokerEndpointSelectionStrategy strategy;

    public BrokerEndpoint selectEndpoint() {
        return strategy.select();
    }
}
