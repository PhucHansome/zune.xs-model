package com.cg.service.orderItems;

import com.cg.model.OrderItem;
import com.cg.model.dto.OrderItemsDTO;
import com.cg.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IOrderItemsService extends IGeneralService<OrderItem> {

    Optional<OrderItemsDTO> findByIdOrderItemsDTO(Long id);

    List<OrderItemsDTO> findByUsername(String username);
}
