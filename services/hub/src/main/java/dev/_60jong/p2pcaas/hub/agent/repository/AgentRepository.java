package dev._60jong.p2pcaas.hub.agent.repository;

import dev._60jong.p2pcaas.hub.agent.domain.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, String> {
}
