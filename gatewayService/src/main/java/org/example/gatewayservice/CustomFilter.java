package org.example.gatewayservice;

import com.netflix.spectator.impl.Config;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {
    public CustomFilter() {
        super(Config.class);
    }
    // apply를 재정의해야 하며 필터의 로직을 정의
    // chain.filter(exchange)를 호출하여 다음 필터로 체인을 전달하기 전에 필요한 작업을 수행
    @Override
    public GatewayFilter apply(Config config) {

        return (exchange,chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Custom Pre filter request id -> {}", request.getId());
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("Custom POST Filter : response code -> {}", response.getStatusCode());
            }));
        };
    }

    @Getter
    @Setter
    public static class Config {
        // 필터와 관련된 설정값을 추가할 수 있음
    }

}
