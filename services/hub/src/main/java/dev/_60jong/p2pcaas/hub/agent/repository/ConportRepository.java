package dev._60jong.p2pcaas.hub.agent.repository;

import dev._60jong.p2pcaas.hub.agent.domain.entity.Conport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConportRepository extends JpaRepository<Conport, String> {
}
