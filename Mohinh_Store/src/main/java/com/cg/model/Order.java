package com.cg.model;

import com.cg.model.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "orders")
@Accessors(chain = true)
public class Order extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shipped_date")
    private Date shippedDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerInfo customerInfo;

    @ManyToOne
    @JoinColumn(name = "orderItems_id")
    private OrderItem orderItem;

    public OrderDTO toOrderDTO(){
        return new OrderDTO()
                .setId(id)
                .setShippedDate(shippedDate)
                .setUser(user.toUserDTO())
                .setCustomerInfo(customerInfo.toCustomerDTO())
                .setOrderItem(orderItem.toOderItemsDTO());
    }
}
