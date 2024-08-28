package org.example.orderservice;

import lombok.Data;

@Data
public class OrderDto {
    private Long id;
    private Long userId;
    private Long catalogId;
    private String productName;
    private Integer stock;
    private Integer unitPrice;
    private String createdAt;


}
