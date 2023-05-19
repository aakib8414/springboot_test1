package com.test.test2.repository;

import com.test.test2.entity.Order;
import com.test.test2.projection.OrderDetailsProjection;
import com.test.test2.projection.OrderProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {

    Optional<OrderProjection> findOrderById(Long id);
    List<OrderDetailsProjection> findAllByAmountNotNull();
    @Query("SELECT new com.test.test2.projection.OrderDetailsProjection(o.id,o.orderStatus,o.amount,o.orderDate) FROM Order o")
    List<OrderDetailsProjection> findAllOrderProjection();
}
