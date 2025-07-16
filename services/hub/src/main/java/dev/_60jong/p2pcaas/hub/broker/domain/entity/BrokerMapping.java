package dev._60jong.p2pcaas.hub.broker.domain.entity;

import dev._60jong.p2pcaas.hub.agent.domain.entity.Conport;
import dev._60jong.p2pcaas.hub.consumer.domain.entity.Consumer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class BrokerMapping {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conport_id")
    private Conport conport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

    @Enumerated(EnumType.STRING)
    private BrokerEndpoint endpoint;

    public BrokerMapping(Conport agent, Consumer consumer, BrokerEndpoint endpoint) {
        this.conport = agent;
        this.consumer = consumer;
        this.endpoint = endpoint;
    }
}
