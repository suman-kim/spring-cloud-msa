package org.example.orderservice;


import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service")
public class OrderController {

    Environment env;
    private final OrderService orderService;

    @GetMapping("/health-check")
    public String status() {
        return String.format("It's Working in Order Service on PORT %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getOrders(){
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/orders/userId/{userId}")
    public ResponseEntity<List<OrderDto>> getOrdersByUserId(@PathVariable("userId") Long userId){
        return new ResponseEntity<>(orderService.findByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/orders/id/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("orderId") Long orderId){
        return new ResponseEntity<>(orderService.findById(orderId), HttpStatus.OK);
    }


    @PostMapping("/orders")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto){
        return new ResponseEntity<>(orderService.createOrder(orderDto), HttpStatus.CREATED);
    }
}
