package com.example.order.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "stock-service", path = "/stock")
public interface StockService {
    @RequestMapping("/deduct")
    Boolean deduct(@RequestParam("productId") Integer productId, @RequestParam("amount") Integer amount);
}
