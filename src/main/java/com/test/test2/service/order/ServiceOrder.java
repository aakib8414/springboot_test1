package com.test.test2.service.order;

import com.test.test2.entity.Order;
import com.test.test2.projection.OrderDetailsProjection;
import com.test.test2.projection.OrderProjection;
import com.test.test2.response.OrderResponse;

import java.util.List;

public interface ServiceOrder {

    Order createOrder(Order order);

    Order updateOrder(Order order);

    List<Order> getAllOrder();

    OrderResponse getOrderById(Long id);

    void deleteOrderById(Long id);
    OrderProjection orderById(Long id);
    List<OrderDetailsProjection> getAllOrderProjection();

}

