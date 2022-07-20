package com.example.order.service.impl;

import com.example.order.dao.OrderDAO;
import com.example.order.mapper.OrderMapper;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public void insert(Integer productId,Integer amount) {
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.setProductId(productId);
        orderDAO.setTotalAmount(amount);
        orderDAO.setStatus(0);
        this.orderMapper.insert(orderDAO);
    }

    @Override
    public void delete(Long id) {
        this.orderMapper.deleteById(id);
    }
}
