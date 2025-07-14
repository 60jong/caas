package dev._60jong.caas.socketbroker.common.feign.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackages = "dev._60jong.caas.socketbroker.common.feign.client")
@Configuration
public class FeignConfig {
}
