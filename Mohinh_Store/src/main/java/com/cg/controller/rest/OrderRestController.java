package com.cg.controller.rest;

import com.cg.model.Order;
import com.cg.model.OrderItem;
import com.cg.model.Product;
import com.cg.model.dto.OrderDTO;
import com.cg.service.order.IOrderService;
import com.cg.service.orderItems.IOrderItemsService;
import com.cg.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderRestController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private AppUtil appUtils;

    @Autowired
    private IOrderItemsService orderItemsService;

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@RequestBody OrderDTO orderDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Optional<OrderItem> optionalOrder = orderItemsService.findById(orderDTO.getOrderItem().getId());
        if (optionalOrder.isPresent()) {
            orderItemsService.softDelete(optionalOrder.get());

            Order order = orderService.save(orderDTO.toOrder());
            return new ResponseEntity<>(order.toOrderDTO(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error Element", HttpStatus.BAD_REQUEST);

        }
    }
}
