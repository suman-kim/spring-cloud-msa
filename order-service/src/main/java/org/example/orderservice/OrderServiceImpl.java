package org.example.orderservice;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.math.raw.Mod;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public OrderDto findById(Long orderId) {
        ModelMapper modelMapper = new ModelMapper();
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Not Found"));
        OrderDto orderDto = modelMapper.map(orderEntity, OrderDto.class);
        return orderDto;
    }


    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        ModelMapper modelMapper = new ModelMapper();

        OrderEntity orderEntity = modelMapper.map(orderDto, OrderEntity.class);

        OrderEntity createEntity = orderRepository.save(orderEntity);

        return modelMapper.map(createEntity, OrderDto.class);

    }

    @Override
    public List<OrderDto> findAll() {
        Iterable<OrderEntity> orderEntities = orderRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        List<OrderDto> orderDtoList = new ArrayList<>();

        orderEntities.forEach(v -> {
            OrderDto orderDto = modelMapper.map(v, OrderDto.class);
            orderDtoList.add(orderDto);
        });

        return orderDtoList;
    }

    @Override
    public List<OrderDto> findByUserId(Long userId) {
        Iterable<OrderEntity> orderEntities = orderRepository.findByUserId(userId);
        ModelMapper modelMapper = new ModelMapper();
        List<OrderDto> orderDtoList = new ArrayList<>();

        orderEntities.forEach(v -> {
            OrderDto orderDto = modelMapper.map(v, OrderDto.class);
            orderDtoList.add(orderDto);
        });

        return orderDtoList;
    }
}
