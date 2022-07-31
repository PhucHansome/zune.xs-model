package com.cg.controller.rest;


import com.cg.exception.ResourceNotFoundException;
import com.cg.model.OrderItem;
import com.cg.model.Product;
import com.cg.model.User;
import com.cg.model.dto.OrderItemsDTO;
import com.cg.service.orderItems.IOrderItemsService;
import com.cg.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orderitems")
public class OrderItemsRestController {

    @Autowired
    private IOrderItemsService orderItemsService;

    @Autowired
    private AppUtil appUtils;


    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@RequestBody OrderItemsDTO orderItemDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }
        orderItemDTO.setGrandTotal(orderItemDTO.getProduct().getPriceProduct());
        orderItemDTO.setQuantityOrdered(new BigDecimal(1));
        OrderItem orderItem = orderItemsService.save(orderItemDTO.toOderItem());
        return new ResponseEntity<>(orderItem.toOderItemsDTO(), HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> showOrderItems(@PathVariable String username){
        List<OrderItemsDTO> orderItemsDTO = orderItemsService.findByUsername(username);
        if (orderItemsDTO.isEmpty()){
            return new ResponseEntity<>("Giỏ hàng rỗng",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderItemsDTO, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getOrderItemById(@PathVariable Long id){
        Optional<OrderItemsDTO> orderItemOptional = orderItemsService.findByIdOrderItemsDTO(id);
        if(!orderItemOptional.isPresent()){
            throw  new ResourceNotFoundException("Invalid User Id");
        }
        return new ResponseEntity<>(orderItemOptional.get().toOderItem(),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{orderItemsId}")
    public ResponseEntity<?> doDelete(@PathVariable Long orderItemsId) {

        Optional<OrderItem> optionalProduct = orderItemsService.findById(orderItemsId);

        if (optionalProduct.isPresent()) {
            orderItemsService.softDelete(optionalProduct.get());
            return new ResponseEntity<>("Delete customer success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error for deleted customer", HttpStatus.BAD_REQUEST);

        }
    }
    @PutMapping("/update/")
    public  ResponseEntity<?> doUpdate(@RequestBody OrderItemsDTO orderItemsDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }


        orderItemsDTO.setGrandTotal(new BigDecimal(String.valueOf(orderItemsDTO.getQuantityOrdered().multiply(orderItemsDTO.getProduct().getPriceProduct()))));
        OrderItem orderItem = orderItemsService.save(orderItemsDTO.toOderItem());

        return new ResponseEntity<>(orderItem.toOderItemsDTO(), HttpStatus.ACCEPTED);
    }

}
