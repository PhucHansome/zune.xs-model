package com.cg.model.dto;

import com.cg.model.Category;
import com.cg.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
public class ProductDTO implements Validator {

    private Long id;

    @NotEmpty(message = "Mã sản phẩm không được để trống")
    private String productCode;

    @NotEmpty(message = "Tên sản phẩm không được để trống")
    private String productName;

    private CategoryDTO category;


    @Min(value = 1, message = "số lượng tối thiểu 1 sản phẩm")
    @Max(value = 999, message = "số lượng không vượt quá 999 sản phẩm")
    private BigDecimal quantityProduct;

    @NotEmpty(message = "Chi tiết sản phẩm không được để trống")
    private String productDescription;

    //    @Pattern(regexp = "(^$|[0-9]*$)", message = "Chỉ được nhập số"))
    @Min(value = 10000, message = "số tiền tối thiểu 10,000VNĐ")
    @Max(value = 999999999, message = "Số tiền không vượt quá 999,999,999VND")
    private BigDecimal priceProduct;

    @NotEmpty(message = "Url Ảnh sản phẩm không được để trống")
    private String image;

    private Date createdAt;

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

    public Product toProduct() {
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


    @Override
    public boolean supports(Class<?> aClass) {
        return ProductDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
