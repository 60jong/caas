package dev.kingkj.caas.socketbroker.agent.config;

import dev.kingkj.caas.relay.connection.store.ConnectionStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgentServerConfig {
    /**
     * Agent Socket 저장용 빈
     */
    @Bean
    public ConnectionStore agentConnectionStore() {
        return new ConnectionStore();
    }

}
