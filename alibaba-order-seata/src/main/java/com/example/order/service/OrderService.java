package com.example.order.service;

public interface OrderService {
    void insert(Integer productId,Integer amount);

    void delete(Long id);
}
