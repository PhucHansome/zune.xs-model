package com.cg.service.orderItems;

import com.cg.model.OrderItem;
import com.cg.model.dto.OrderItemsDTO;
import com.cg.repository.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemItemsServiceImpl implements IOrderItemsService {

    @Autowired
    private OrderItemsRepository orderItemsRepository;


    @Override
    public List<OrderItem> findAll() {
        return orderItemsRepository.findAll();
    }

    @Override
    public Boolean existById(Long id) {
        return orderItemsRepository.existsById(id);
    }

    @Override
    public Optional<OrderItem> findById(Long id) {
        return orderItemsRepository.findById(id);
    }

    @Override
    public OrderItem getById(Long id) {
        return orderItemsRepository.getById(id);
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemsRepository.save(orderItem);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void softDelete(OrderItem orderItem) {
        orderItem.setDeleted(true);
        orderItemsRepository.save(orderItem);
    }

    @Override
    public List<OrderItemsDTO> findAllOrderItemsDTO(String username) {
        return orderItemsRepository.findAllOrderItemsDTO(username);
    }

    @Override
    public Optional<OrderItemsDTO> findByIdOrderItemsDTO(Long id) {
        return orderItemsRepository.findByIdOrderItemsDTO(id);
    }

    @Override
    public List<OrderItemsDTO> findByUsername(String username) {
        return orderItemsRepository.findAllOrderItemsDTO(username);
    }
}
