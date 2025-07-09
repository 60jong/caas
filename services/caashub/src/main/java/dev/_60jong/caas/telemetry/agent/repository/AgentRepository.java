package dev._60jong.caas.telemetry.agent.repository;

import dev._60jong.caas.telemetry.agent.domain.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, String> {
}
