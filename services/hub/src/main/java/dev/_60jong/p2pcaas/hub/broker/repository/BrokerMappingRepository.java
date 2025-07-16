package dev._60jong.p2pcaas.hub.broker.repository;

import dev._60jong.p2pcaas.hub.broker.domain.entity.BrokerEndpoint;
import dev._60jong.p2pcaas.hub.broker.domain.entity.BrokerMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrokerMappingRepository extends JpaRepository<BrokerMapping, Long> {

    @Query("select bm.conport.id " +
            "from BrokerMapping bm " +
            "where bm.endpoint = :endpoint " +
            "  and bm.consumer.id = :consumerId")
    Optional<String> findMappedConportId(BrokerEndpoint endpoint, String consumerId);
}
