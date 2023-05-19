package com.test.test2.repository;

import com.test.test2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findAllByProductIdIn(List<Long> productIds);
}
