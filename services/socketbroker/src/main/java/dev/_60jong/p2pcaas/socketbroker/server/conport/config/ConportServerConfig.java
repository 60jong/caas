package dev._60jong.p2pcaas.socketbroker.server.conport.config;

import dev._60jong.p2pcaas.socketbroker.connection.store.ConnectionStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConportServerConfig {
    /**
     * Conport Socket 저장용 빈
     */
    @Bean
    public ConnectionStore conportConnectionStore() {
        return new ConnectionStore();
    }

}
