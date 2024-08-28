package org.example.gatewayservice;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {
    public LoggingFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {


        GatewayFilter filter = new OrderedGatewayFilter((exchange, chain) -> {

            log.info("Logging filter baseMessage: {}", config.getBaseMessage());
            log.info("Logging PRE filter: request id -> {}", exchange.getRequest().getId());


            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("Logging POST filter: response code -> {}", exchange.getResponse().getStatusCode());
            }));
            // OrderedGatewayFilter를 사용하여 필터의 우선순위를 지정
        }, OrderedGatewayFilter.LOWEST_PRECEDENCE);
        return filter;
    }

    @Data
    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
