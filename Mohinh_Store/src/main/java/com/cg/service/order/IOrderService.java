package com.cg.service.order;

import com.cg.model.Order;
import com.cg.model.dto.OrderDTO;
import com.cg.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IOrderService extends IGeneralService<Order> {
    List<OrderDTO> findAllOrderDTO();

    Optional<OrderDTO> findByIdOrderDTO(Long id);
}
