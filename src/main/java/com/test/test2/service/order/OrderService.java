package com.test.test2.service.order;

import com.test.test2.dto.ProductDto;
import com.test.test2.entity.Order;
import com.test.test2.entity.Product;
import com.test.test2.exception.ProductServiceCustomException;
import com.test.test2.projection.OrderDetailsProjection;
import com.test.test2.projection.OrderProjection;
import com.test.test2.repository.OrderRepo;
import com.test.test2.repository.ProductRepo;
import com.test.test2.response.OrderResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderService implements ServiceOrder {

    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;


    @Override
    public Order createOrder(Order order) {

        order.setOrderStatus("order Confirmed");
        long totalAmount = 0;
        final List<ProductDto> cartProductList = order.getProductList();
        final List<Long> idList = cartProductList.stream().map(ProductDto::getProductId).collect(Collectors.toList());
        final List<Product> dbProductList = productRepo.findAllByProductIdIn(idList);
        for (int i = 0, j = 0; i < dbProductList.size(); i++, j++) {
            final ProductDto cartProduct = cartProductList.get(j);
            final Product dbProduct = dbProductList.get(i);
            final long availableQty = dbProduct.getQuantity();
            cartProduct.setPrice(dbProduct.getPrice());
            if (availableQty < cartProduct.getQuantity()) {
                throw new ProductServiceCustomException(
                        "Product does not have sufficient Quantity",
                        "INSUFFICIENT_QUANTITY"
                );
            }

            final long price = cartProduct.getQuantity() * dbProduct.getPrice();
            totalAmount = totalAmount + price;
            dbProduct.setQuantity(availableQty - cartProduct.getQuantity());
        }

        order.setAmount(totalAmount);
        productRepo.saveAll(dbProductList);
        order = orderRepo.save(order);
        return order;

    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepo.save(order);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepo.findAll();
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Oerder not exist for ID :" + id));
        return OrderResponse
                .builder()
                .orderDate(order.getOrderDate())
                .OrderStatus(order.getOrderStatus())
                .productList(order.getProductList())
                .totalAmount(order.getAmount())
                .id(order.getId())
                .build();

    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepo.deleteById(id);
    }

    @Override
    public OrderProjection orderById(Long id) {
        final OrderProjection order = orderRepo.findOrderById(id).orElseThrow(() -> new EntityNotFoundException("Oerder not exist for ID :" + id));
        return order;
    }

    @Override
    public List<OrderDetailsProjection> getAllOrderProjection() {
        final List<OrderDetailsProjection> projectionList = orderRepo.findAllOrderProjection();
        return projectionList;
    }
}
