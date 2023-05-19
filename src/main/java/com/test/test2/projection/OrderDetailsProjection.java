package com.test.test2.projection;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class OrderDetailsProjection implements Serializable {
    private static final long serialVersionUID = -7392172999600013610L;
    private long id;
    private String orderStatus;
    private long amount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderDate;

    public OrderDetailsProjection(long id, String orderStatus, long amount, LocalDateTime orderDate) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.amount = amount;
        this.orderDate = orderDate;
    }
}
