package com.cg.model.dto;

import com.cg.model.OrderItem;
import com.cg.model.Product;
import com.cg.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
public class OrderItemsDTO {

    private Long id;

    private ProductDTO product;

    private BigDecimal quantityOrdered;

    private BigDecimal priceEach;

    private BigDecimal grandTotal;

    private String user;

    public OrderItemsDTO(Long id, Product product, BigDecimal quantityOrdered, BigDecimal priceEach, BigDecimal grandTotal, String user){
        this.id = id;
        this.product =  product.toProductDTO();
        this.quantityOrdered = quantityOrdered;
        this.priceEach = priceEach;
        this.grandTotal = grandTotal;
        this.user = user;
    }

    public OrderItem toOderItem(){
        return new OrderItem()
                .setId(id)
                .setProduct(product.toProduct())
                .setQuantityOrdered(quantityOrdered)
                .setPriceEach(priceEach)
                .setGrandTotal(grandTotal)
                .setUser(user);
    }
}
