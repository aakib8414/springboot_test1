package com.test.test2.service.product;

import com.test.test2.entity.Product;
import com.test.test2.exception.ProductServiceCustomException;
import com.test.test2.repository.ProductRepo;
import com.test.test2.request.Request;
import com.test.test2.response.Response;
import com.test.test2.service.ProductSpecificationBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductService implements Serivice {


    private final ProductRepo productRepo;

    @Override
    public Product addProduct(Request productRequest) {
        Product product = Product.builder().productName(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();
        product = productRepo.save(product);
        return product;

    }

    @Override
    public Page<Product> getAllProducts(int page, int size, String sort) {
        final Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sort));
//        final Page<Product> pageList = repo.findAll(pageable);
//        final List<Product> list = pageList.getContent();
//        if (list.size() == 0)
//            throw new ProductServiceCustomException("Product List is empty", "Product_Not_Found");
        return productRepo.findAll(pageable);
    }

    @Override
    public Response getProductById(long productId) {
        Product product = productRepo.findById(productId).orElseThrow(
                () -> new ProductServiceCustomException("Product with given Id " + productId + " not found", "PRODUCT_NOT_FOUND"));
        Response productResponse
                = new Response();
        copyProperties(product, productResponse);
        log.info("ProductService | getProductById | productResponse :" + productResponse);
        return productResponse;
    }

    @Override
    public void decreaseQuantity(long productId, long quantity) {
        log.info("Reduce Quantity {} for Id: {}", quantity, productId);

        Product product
                = productRepo.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException(
                        "Product with given Id not found",
                        "PRODUCT_NOT_FOUND"
                ));

        if (product.getQuantity() < quantity) {
            throw new ProductServiceCustomException(
                    "Product does not have sufficient Quantity",
                    "INSUFFICIENT_QUANTITY"
            );
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepo.save(product);
        log.info("Product Quantity updated Successfully");
    }

    @Override
    public void deleteProductById(long productId) {
        if (!productRepo.existsById(productId)) {
            throw new ProductServiceCustomException(
                    "Product with given with Id: " + productId + " not found:",
                    "PRODUCT_NOT_FOUND");
        }
        log.info("Deleting Product with id: {}", productId);
        productRepo.deleteById(productId);
    }

    @Override
    public void updateProduct(Product product) {
        productRepo.save(product);
    }

    public Page<Product> findByQtyAndPrice(long qty, long price, int page, int size) {

        final Pageable pageable = PageRequest.of(page, size);
        Specification<Product> spec = Specification.where(
                        ProductSpecificationBuilder.builder().build().hasQnty(qty))
                .and(ProductSpecificationBuilder.builder().build().hasPrice(price));
        final Page<Product> productList = productRepo.findAll(spec, pageable);
        return productList;
    }
}
