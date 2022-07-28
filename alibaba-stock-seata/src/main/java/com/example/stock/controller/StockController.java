package com.example.stock.controller;

import com.example.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {
    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @RequestMapping("/deduct")
    public Boolean deduct(@RequestParam("productId") Integer productId, @RequestParam("amount") Integer amount) {
        return this.stockService.deduct(productId, amount);
    }

    @GetMapping("/get/{productId}")
    public Integer getStock(@PathVariable("productId") Integer productId) {
        return this.stockService.getStock(productId);
    }
}
