package dev._60jong.caas.hub.broker.web.api;

import dev._60jong.caas.hub.broker.domain.entity.BrokerEndpoint;
import dev._60jong.caas.hub.broker.service.BrokerMappingService;
import dev._60jong.caas.hub.broker.web.request.BrokerMappingRequest;
import dev._60jong.caas.hub.broker.web.response.BrokerMappingResponse;
import dev._60jong.caas.shared.web.response.ApiResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/broker")
public class BrokerMappingApiController {

    private final BrokerMappingService brokerMappingService;

    @GetMapping("/{brokerEndpoint}/mapping/conport/id")
    public ApiResponse<String> getConportId(
            @PathVariable("brokerEndpoint") String brokerEndpoint,
            @RequestParam("consumer_id") String consumerId
    ) {
        final BrokerEndpoint endpoint = BrokerEndpoint.getByName(brokerEndpoint);

        return ApiResponse.success(brokerMappingService.findMappedConportId(endpoint, consumerId));
    }

    @PostMapping("/mapping")
    public ApiResponse<BrokerMappingResponse> registerMapping(@RequestBody BrokerMappingRequest request) {
        return ApiResponse.success(brokerMappingService.createMapping(request.conportId(),
                                                                      request.consumerId()));
    }
}
