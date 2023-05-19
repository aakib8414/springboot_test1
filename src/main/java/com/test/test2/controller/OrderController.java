package com.test.test2.controller;

import com.test.test2.entity.Order;
import com.test.test2.projection.OrderDetailsProjection;
import com.test.test2.projection.OrderProjection;
import com.test.test2.response.OrderResponse;
import com.test.test2.service.order.ServiceOrder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Log4j2
public class OrderController {

    private final ServiceOrder orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
        final Order createdOrder = orderService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<Order> getOrderById(@PathVariable long id) {
//        final Order order = orderService.getOrderById(id);
//        return new ResponseEntity<>(order, HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderProjection> getOrderByIdProjection(@PathVariable long id) {

        final OrderProjection order = orderService.orderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable long id) {
        orderService.deleteOrderById(id);
        return new ResponseEntity<>("Order has been deleted for gven id " + id, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDetailsProjection>> getAllOrderProjection() {
        final List<OrderDetailsProjection> projectionList = orderService.getAllOrderProjection();
        return new ResponseEntity<>(projectionList, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable long id) {
        final OrderResponse order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
