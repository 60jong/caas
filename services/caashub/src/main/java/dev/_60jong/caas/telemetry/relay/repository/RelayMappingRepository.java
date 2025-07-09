package dev._60jong.caas.telemetry.relay.repository;

import dev._60jong.caas.telemetry.agent.domain.entity.Agent;
import dev._60jong.caas.telemetry.relay.domain.entity.RelayMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RelayMappingRepository extends JpaRepository<RelayMapping, Long> {

    @Query("select rm.agent.id from RelayMapping rm where rm.clientIp = :clientIp")
    String findAgentIdByClientIp(String clientIp);

}
