package org.example.orderservice;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long catalogId;
    private String productName;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private Date createdAt;



}
