package com.example.gateway.predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class CheckAuthRoutePredicateFactory extends AbstractRoutePredicateFactory<CheckAuthRoutePredicateFactory.Config> {

    public CheckAuthRoutePredicateFactory() {
        super(Config.class);
    }

    /**
     * 请求头必须包含 "token"，且 token 必须以 "Bear" 开头
     * @param config
     * @return
     */
    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                List<String> tokens = serverWebExchange.getRequest().getHeaders().get(config.getName());
                if (tokens != null && tokens.size() > 0) {
                    return tokens.get(0).startsWith(config.getPrefix());
                }
                return false;
            }
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("name", "prefix");
    }

    public static class Config{
        private String name;
        private String prefix;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }
    }
}
