package com.cg.model;

import com.cg.model.dto.OrderItemsDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "order_items")
public class OrderItem  extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_code")
    private Product product;

    @Column(name = "quantity_order")
    private BigDecimal quantityOrdered;

    @Column(name = "price_Each")
    private BigDecimal priceEach;

    @Column(name = "grand_total")
    private BigDecimal grandTotal;

    @Column(name = "user_name")
    private String  user;

    public OrderItemsDTO toOderItemsDTO(){
        return new OrderItemsDTO()
                .setId(id)
                .setProduct(product.toProductDTO())
                .setQuantityOrdered(quantityOrdered)
                .setPriceEach(priceEach)
                .setGrandTotal(grandTotal)
                .setUser(user)
                ;
    }

}
