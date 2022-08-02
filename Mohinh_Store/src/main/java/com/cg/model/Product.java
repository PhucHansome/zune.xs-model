package com.cg.model;

import com.cg.model.dto.ProductDTO;
import com.cg.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "products")
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_code", nullable = false)
    private String productCode;

    @Column(name = "product_name")
    private String productName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "quantity_product")
    private BigDecimal quantityProduct;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "price_product")
    private BigDecimal priceProduct;

    @Column(name ="images")
    private String image;


    public ProductDTO toProductDTO() {
        return new ProductDTO()
                .setId(id)
                .setProductCode(productCode)
                .setProductName(productName)
                .setCategory(category.categoryDTO())
                .setQuantityProduct(quantityProduct)
                .setProductDescription(productDescription)
                .setPriceProduct(priceProduct)
                .setImage(image)
                ;


    }
}
