package com.test.test2.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.test.test2.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class OrderResponse {

    private long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderDate;
    private String OrderStatus;
    private long totalAmount;
    private List<ProductDto> productList;

}
