package dev._60jong.p2pcaas.socketbroker.common.feign.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackages = "dev._60jong.p2pcaas.socketbroker.common.feign.client")
@Configuration
public class FeignConfig {
}
