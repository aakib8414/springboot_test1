package com.test.test2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.test2.dto.ProductDto;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDER_DETAILS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    @Column(name = "PRODUCT_ID")
//    private long productId;

//    private long quantity;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ORDER_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderDate ;

    private String orderStatus;
    @Column(name = "totalAmount")
    private long amount;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "order_product",
//            joinColumns = {@JoinColumn(name = "order_id")},
//            inverseJoinColumns = {@JoinColumn(name = "product_id")})
//    private List<Product> products = new ArrayList<>();

    //    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    @JoinColumn(name = "order_product_id")

//    @ManyToMany(cascade = CascadeType.MERGE)
//    @JoinTable(name = "order_product_rel",
//            joinColumns = {@JoinColumn(name = "order_id")}, inverseJoinColumns = {@JoinColumn(name = "product_id")})
//    @JsonIgnoreProperties("orders")
//    private List<Product> productList = new ArrayList<>();

    @ElementCollection
    private List<ProductDto> productList = new ArrayList<>();

    @PrePersist
    private void onCreate() {
        this.orderDate = LocalDateTime.now();
    }
}