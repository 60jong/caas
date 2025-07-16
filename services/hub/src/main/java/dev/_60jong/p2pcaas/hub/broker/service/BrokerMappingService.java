package dev._60jong.p2pcaas.hub.broker.service;

import dev._60jong.p2pcaas.hub.agent.domain.entity.Conport;
import dev._60jong.p2pcaas.hub.agent.repository.ConportRepository;
import dev._60jong.p2pcaas.hub.broker.domain.entity.BrokerEndpoint;
import dev._60jong.p2pcaas.hub.broker.domain.entity.BrokerMapping;
import dev._60jong.p2pcaas.hub.broker.repository.BrokerMappingRepository;
import dev._60jong.p2pcaas.hub.broker.service.endpoint.selector.BrokerEndpointSelector;
import dev._60jong.p2pcaas.hub.broker.web.response.BrokerMappingResponse;
import dev._60jong.p2pcaas.hub.consumer.domain.entity.Consumer;
import dev._60jong.p2pcaas.hub.consumer.repository.ConsumerRepository;
import dev._60jong.p2pcaas.shared.exception.CaasException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Slf4j
@RequiredArgsConstructor
@Service
public class BrokerMappingService {

    private final ConportRepository conportRepository;
    private final ConsumerRepository consumerRepository;
    private final BrokerMappingRepository brokerMappingRepository;
    private final BrokerEndpointSelector endpointSelector;

    @Transactional
    public BrokerMappingResponse createMapping(final String conportId, final String consumerId) {
        final Conport conport = conportRepository.findById(conportId).orElseThrow();
        final Consumer consumer = consumerRepository.findById(consumerId).orElseThrow();

        BrokerEndpoint endpoint = endpointSelector.selectEndpoint();
        log.info("Selected endpoint for consumer ({}) is {}", consumerId, endpoint);

        final BrokerMapping brokerMapping = new BrokerMapping(conport, consumer, endpoint);
        brokerMappingRepository.save(brokerMapping);

        return new BrokerMappingResponse(consumerId, format("%s.socketbroker.caas.60jong.dev:6006", endpoint.name().toLowerCase()));
    }

    public String findMappedConportId(final BrokerEndpoint endpoint, final String consumerId) {
        return brokerMappingRepository.findMappedConportId(endpoint, consumerId)
                .orElseThrow(() -> new CaasException(format("Could not find mapped conport for Consumer (%s) in Endpoint (%s)",  consumerId, endpoint.name())));
    }
}
