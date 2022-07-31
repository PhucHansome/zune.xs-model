package com.cg.repository;

import com.cg.model.OrderItem;
import com.cg.model.dto.CategoryDTO;
import com.cg.model.dto.OrderItemsDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItem,Long> {

    @Query("SELECT new com.cg.model.dto.OrderItemsDTO (" +
            "o.id, " +
            "o.product, " +
            "o.quantityOrdered, " +
            "o.priceEach, " +
            "o.grandTotal, " +
            "o.user " +
            ") " +
            "FROM OrderItem AS o " +
            "WHERE o.user =  ?1 and o.deleted = false "
    )
    List<OrderItemsDTO> findAllOrderItemsDTO(String username);

    @Query("SELECT new com.cg.model.dto.OrderItemsDTO (" +
            "o.id, " +
            "o.product, " +
            "o.quantityOrdered, " +
            "o.priceEach, " +
            "o.grandTotal, " +
            "o.user " +
            ") " +
            "FROM OrderItem AS o " +
            "WHERE o.id =  ?1"
    )
    Optional<OrderItemsDTO>findByIdOrderItemsDTO(Long id);
}
