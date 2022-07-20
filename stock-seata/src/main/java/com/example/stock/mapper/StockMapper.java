package com.example.stock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StockMapper {
    void deduct(@Param("productId") Integer productId, @Param("amount") Integer amount);

    Integer selectCount(@Param("productId") Integer productId);
}
