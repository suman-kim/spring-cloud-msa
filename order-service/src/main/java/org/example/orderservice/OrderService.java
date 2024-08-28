package org.example.orderservice;


import java.util.List;

public interface OrderService {

    OrderDto findById(Long orderId);
    OrderDto createOrder(OrderDto orderDto);
    List<OrderDto> findAll();

    List<OrderDto> findByUserId(Long userId);
}
