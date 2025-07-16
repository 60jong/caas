package dev._60jong.p2pcaas.socketbroker.server.consumer.config;

import dev._60jong.p2pcaas.socketbroker.connection.store.ConnectionStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerServerConfig {
    /**
     * Consumer Socket 저장용 빈
     */
    @Bean
    public ConnectionStore consumerConnectionStore() {
        return new ConnectionStore();
    }

}
