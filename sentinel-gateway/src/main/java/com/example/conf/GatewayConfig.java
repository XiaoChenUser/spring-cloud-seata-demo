package com.example.conf;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Configuration
public class GatewayConfig {
    /**
     * 网关统一异常处理
     */
    @PostConstruct
    public void init(){
        BlockRequestHandler blockRequestHandler = new BlockRequestHandler(){

            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange exchange, Throwable t) {
                if (t instanceof FlowException) {
                    return ServerResponse.status(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(new ErrorResult(HttpStatus.TOO_MANY_REQUESTS.value(),"被流控了"));
                } else if (t instanceof DegradeException) {
                    return ServerResponse.status(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(new ErrorResult(HttpStatus.GATEWAY_TIMEOUT.value(), "被降级了"));
                } else if (t instanceof BlockException) {
                    return ServerResponse.status(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(new ErrorResult(HttpStatus.REQUEST_TIMEOUT.value(), "sentinel block exception"));
                }else {
                    return ServerResponse.status(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(new ErrorResult(10000, "内部异常"));
                }
            }
        };

        GatewayCallbackManager.setBlockHandler(blockRequestHandler);
    }

    private static class ErrorResult {
        private final int code;
        private final String message;

        ErrorResult(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
