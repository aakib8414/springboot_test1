package com.test.test2.controller;

import com.test.test2.entity.Product;
import com.test.test2.request.Request;
import com.test.test2.response.Response;
import com.test.test2.service.product.Serivice;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Log4j2
public class ProductController {

    private final Serivice productService;

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Request productRequest) {

        log.info("ProductController | addProduct is called");
        log.info("ProductController | addProduct | productRequest : " + productRequest.toString());

        Product product = productService.addProduct(productRequest);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getProductById(@PathVariable("id") long productId) {

        log.info("ProductController | getProductById is called");

        log.info("ProductController | getProductById | productId : {}" + productId);

        Response productResponse
                = productService.getProductById(productId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PutMapping("/decProduct/{id}")
    public ResponseEntity<String> decreaseQuantity(
            @PathVariable("id") long productId,
            @RequestParam("qty") long quantity
    ) {

        log.info("ProductController | Decrease Quantity is called");

        log.info("ProductController | Quantity | productId : " + productId);
        log.info("ProductController | Decrease Quantity | quantity : " + quantity);

        productService.decreaseQuantity(productId, quantity);
        return new ResponseEntity<>("Quantity Successfully decreased ", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id") long productId) {

        productService.deleteProductById(productId);
        return new ResponseEntity<>("Product deleted Successfully from db", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Product>> getAllProduct(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "4") int size,
            @RequestParam(name = "sortBy", required = false, defaultValue = "productId") String sort
    ) {
        return new ResponseEntity<>(productService.getAllProducts(page, size, sort), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getProductByQntyAndName(@RequestParam("qty") long qnty,
                                                                 @RequestParam("price") long price,
                                                                 @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                                 @RequestParam(name = "size", required = false, defaultValue = "2") int size) {
        final Page<Product> productList = productService.findByQtyAndPrice(qnty, price,page,size);
        return new ResponseEntity<>(productList, HttpStatus.OK);

    }
}
