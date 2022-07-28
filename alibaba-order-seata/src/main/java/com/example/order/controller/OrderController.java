package com.example.order.controller;

import com.example.order.api.StockService;
import com.example.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final StockService stockService;


    @Autowired
    public OrderController(OrderService orderService, StockService stockService) {
        this.orderService = orderService;
        this.stockService = stockService;
    }

    @GlobalTransactional
    @RequestMapping("/add")
    public String addOrder(@RequestParam("productId") Integer productId, @RequestParam("amount") Integer amount) {
        orderService.insert(productId, amount);
//        Map<String, Object> params = new HashMap<>();
//        params.put("productId", productId);
//        params.put("amount", amount);
//        restTemplate.getForObject("http://localhost:10002/stock/deduct?productId={productId}&amount={amount}", Boolean.class, params);

        stockService.deduct(productId, amount);

//        int a = 1/0;
        log.info("新增订单 - [product id]:" + productId + ",[amount]:" + amount);
        return "新增订单成功";
    }
}
