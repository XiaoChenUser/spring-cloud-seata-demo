package com.example.order.controller;

import com.example.order.service.OrderService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Log
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    public final RestTemplate restTemplate;

    @Autowired
    public OrderController(OrderService orderService, RestTemplate restTemplate) {
        this.orderService = orderService;
        this.restTemplate = restTemplate;
    }

    @Transactional
    @RequestMapping("/add")
    public String addOrder(@RequestParam("productId") Integer productId, @RequestParam("amount") Integer amount) {
        orderService.insert(productId, amount);
        Map<String, Object> params = new HashMap<>();
        params.put("productId", productId);
        params.put("amount", amount);
        restTemplate.getForObject("http://localhost:10002/stock/deduct?productId={productId}&amount={amount}", Boolean.class, params);

        int a = 1/0;
        log.info("新增订单 - [product id]:" + productId + ",[amount]:" + amount);
        return "新增订单成功";
    }
}
