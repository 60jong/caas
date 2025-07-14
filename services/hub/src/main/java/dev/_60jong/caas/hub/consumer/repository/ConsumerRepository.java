package dev._60jong.caas.hub.consumer.repository;

import dev._60jong.caas.hub.consumer.domain.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, String> {
}
