package com.test.test2.dto;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class ProductDto {

    private long productId;
    private String productName;
    private long price;
    private long quantity;
}
