package dev._60jong.caas.hub.broker.config;

import dev._60jong.caas.hub.broker.service.endpoint.selector.strategy.BrokerEndpointSelectionStrategy;
import dev._60jong.caas.hub.broker.service.endpoint.selector.strategy.RoundRobinSelectionStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrokerMappingConfig {
    @Bean
    public BrokerEndpointSelectionStrategy selectionStrategy() {
        return new RoundRobinSelectionStrategy();
    }
}
