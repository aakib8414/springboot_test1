package com.test.test2.service.product;

import com.test.test2.entity.Product;
import com.test.test2.request.Request;
import com.test.test2.response.Response;
import org.springframework.data.domain.Page;

public interface Serivice {
    Product addProduct(Request productRequest);

    Page<Product> getAllProducts(int page, int size, String sort);

    Response getProductById(long productId);

    void decreaseQuantity(long productId, long quantity);

    void deleteProductById(long productId);

    void updateProduct(Product product);

    Page<Product> findByQtyAndPrice(long qty, long price, int page, int size);
}
