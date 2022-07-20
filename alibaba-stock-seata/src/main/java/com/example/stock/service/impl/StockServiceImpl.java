package com.example.stock.service.impl;

import com.example.stock.mapper.StockMapper;
import com.example.stock.service.StockService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Log
@Service
public class StockServiceImpl implements StockService {
    private final StockMapper stockMapper;

    public StockServiceImpl(StockMapper stockMapper) {
        this.stockMapper = stockMapper;
    }

    @Override
    public Boolean deduct(Integer productId, Integer amount) {
        Integer count = this.stockMapper.selectCount(productId);
        if (count == null || count < amount) {
            log.info("库存不足");
            return false;
        }
        this.stockMapper.deduct(productId, amount);
        return true;
    }
}
