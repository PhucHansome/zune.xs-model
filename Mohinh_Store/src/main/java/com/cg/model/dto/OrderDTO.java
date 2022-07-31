package com.cg.model.dto;


import com.cg.model.CustomerInfo;
import com.cg.model.Order;
import com.cg.model.OrderItem;
import com.cg.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
public class OrderDTO {

    private Long id;

    private Date shippedDate;

   private UserDTO user;

   private CustomerInfoDTO customerInfo;

   private OrderItemsDTO orderItem;

    public OrderDTO(Long id, Date shippedDate, User user, CustomerInfo customerInfo, OrderItem orderItem) {
        this.id = id;
        this.shippedDate = shippedDate;
        this.user = user.toUserDTO();
        this.customerInfo = customerInfo.toCustomerDTO();
        this.orderItem = orderItem.toOderItemsDTO();
    }

    public Order toOrder(){
        return new Order()
                .setId(id)
                .setShippedDate(shippedDate)
                .setUser(user.toUser())
                .setCustomerInfo(customerInfo.toCustomer())
                .setOrderItem(orderItem.toOderItem());
    }
}
