package dev._60jong.p2pcaas.hub.consumer.repository;

import dev._60jong.p2pcaas.hub.consumer.domain.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, String> {
}
