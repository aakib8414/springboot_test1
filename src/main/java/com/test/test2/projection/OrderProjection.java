package com.test.test2.projection;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public interface OrderProjection {
    String getOrderStatus();

    Long getAmount();

    //    List<ProductDto> getProductList();
    @JsonFormat(pattern = "dd-MM-yyyy-hh:mm")
    LocalDateTime getOrderDate();
}
