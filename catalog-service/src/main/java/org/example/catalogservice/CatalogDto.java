package org.example.catalogservice;

import lombok.Data;

@Data
public class CatalogDto {
    private Long id;
    private String productId;
    private String productName;
    private Integer stock;
    private Integer unitPrice;
    private String createdAt;

    private String orderId;
    private Long userId;
}
