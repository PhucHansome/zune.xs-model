package com.cg.controller.rest;

import com.cg.exception.ResourceNotFoundException;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/allorder")
    public ResponseEntity<?>showListOrder(){
        List<OrderDTO> orderList = orderService.findAllOrderDTO();
        return new ResponseEntity<>(orderList,HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id){
        Optional<OrderDTO> orderDTO = orderService.findByIdOrderDTO(id);

        if(!orderDTO.isPresent()){
            throw new ResourceNotFoundException("Invalid User Id");
        }
        return new ResponseEntity<>(orderDTO.get().toOrder(), HttpStatus.OK);
    }

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
