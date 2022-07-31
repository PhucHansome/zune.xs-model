package com.cg.repository;

import com.cg.model.CustomerInfo;
import com.cg.model.Product;
import com.cg.model.dto.ProductDTO;
import com.cg.model.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT NEW com.cg.model.dto.ProductDTO (p.id, " +
            "p.productCode, " +
            "p.productName, " +
            "p.category, " +
            "p.quantityProduct, " +
            "p.productDescription, " +
            "p.priceProduct, " +
            "p.image," +
            "p.createdAt, " +
            "p.updatedAt )  " +
            "FROM Product p  WHERE p.deleted = false ")
    List<ProductDTO> findAllProductDTO();
}
