package dev._60jong.caas.socketbroker.common.feign.client;

import dev._60jong.caas.shared.web.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "HubFeignClient",
        url = "${caas.hub.server.url}"
)
public interface HubClient {
    @GetMapping("/api/v1/broker/{brokerEndpoint}/conport/id")
    ApiResponse<String> getConportId(
            @PathVariable("brokerEndpoint") String brokerEndpoint,
            @RequestParam("consumer_id") String consumerId
    );
}
