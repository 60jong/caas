package dev._60jong.caas.hub.agent.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Agent {
    @Id
    private String id; // encrypted agent host ip

    private String name;

    @OneToMany(mappedBy = "agent")
    private List<Conport> conports = new ArrayList<>();

    public Agent(final String name, final String agentId) {
        this.name = name;
        this.id = agentId;
    }
}
