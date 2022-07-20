package com.example.order.dao;

import lombok.Data;

@Data
public class OrderDAO {
    private Long id;
    private Integer productId;
    private Integer totalAmount;
    private Integer status;
}
