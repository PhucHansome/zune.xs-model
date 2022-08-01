package com.cg.repository;

import com.cg.model.Order;
import com.cg.model.OrderItem;
import com.cg.model.dto.OrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("SELECT NEW com.cg.model.dto.OrderDTO (o.id, " +
            "o.shippedDate, " +
            "o.user, " +
            "o.customerInfo, " +
            "o.orderItem, " +
            "o.status " +
            " )  " +
            "FROM Order o  WHERE o.deleted = false ")
    List<OrderDTO> findAllOrderDTO();

    @Query("SELECT NEW com.cg.model.dto.OrderDTO (o.id, " +
            "o.shippedDate, " +
            "o.user, " +
            "o.customerInfo, " +
            "o.orderItem, " +
            "o.status " +
            " )  " +
            "FROM Order o  WHERE o.id = ?1 ")
    Optional<OrderDTO> findByIdOrderDTO(Long id);
}
