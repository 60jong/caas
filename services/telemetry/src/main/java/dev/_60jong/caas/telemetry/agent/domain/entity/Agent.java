package dev._60jong.caas.telemetry.agent.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Agent {
    @Id
    private String id;

    public Agent(String agentId) {
        this.id = agentId;
    }
}
