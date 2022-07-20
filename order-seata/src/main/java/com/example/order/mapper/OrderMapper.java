package com.example.order.mapper;

import com.example.order.dao.OrderDAO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMapper {
    void insert(OrderDAO orderDAO);

    void deleteById(@Param("id") Long id);
}
