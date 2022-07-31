package com.cg.service.order;

import com.cg.model.Order;
import com.cg.model.OrderItem;
import com.cg.model.dto.OrderItemsDTO;
import com.cg.repository.OrderItemsRepository;
import com.cg.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl  implements IOrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Boolean existById(Long id) {
        return null;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Order getById(Long id) {
        return null;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void softDelete(Order order) {

    }
}
