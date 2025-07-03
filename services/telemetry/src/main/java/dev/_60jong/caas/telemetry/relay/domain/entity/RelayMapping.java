package dev._60jong.caas.telemetry.relay.domain.entity;

import dev._60jong.caas.telemetry.agent.domain.entity.Agent;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RelayMapping {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @Column(unique = true)
    private String clientIp;

    public RelayMapping(Agent agent, String clientIp) {
        this.agent = agent;
        this.clientIp = clientIp;
    }
}
