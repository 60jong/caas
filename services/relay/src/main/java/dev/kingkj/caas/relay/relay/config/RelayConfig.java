package dev.kingkj.caas.relay.relay.config;

import dev.kingkj.caas.relay.connection.store.ConnectionStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RelayConfig {
    /**
     * Relay Socket 저장용 빈
     */
    @Bean
    public ConnectionStore relayConnectionStore() {
        return new ConnectionStore();
    }

}
