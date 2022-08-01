package com.cg.model.dto;

import com.cg.model.BaseEntity;
import com.cg.model.Category;
import com.cg.model.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
public class ProductDTO  {

    private Long id;

    private String productCode;

    private String productName;

    private CategoryDTO category;

    private BigDecimal quantityProduct;

    private String productDescription;

    private BigDecimal priceProduct;

    private String image;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date updatedAt;

    public ProductDTO(Long id, String productCode, String productName, Category category, BigDecimal quantityProduct, String productDescription, BigDecimal priceProduct, String image, Date createdAt, Date updatedAt) {
        this.id = id;
        this.productCode = productCode;
        this.productName = productName;
        this.category = category.categoryDTO();
        this.quantityProduct = quantityProduct;
        this.productDescription = productDescription;
        this.priceProduct = priceProduct;
        this.image = image;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Product toProduct(){
        return new Product()
                .setId(id)
                .setProductCode(productCode)
                .setProductName(productName)
                .setCategory(category.toCategory())
                .setQuantityProduct(quantityProduct)
                .setProductDescription(productDescription)
                .setPriceProduct(priceProduct)
                .setImage(image)
                ;
    }
}
