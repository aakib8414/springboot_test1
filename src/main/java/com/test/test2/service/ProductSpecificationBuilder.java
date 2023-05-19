package com.test.test2.service;

import com.test.test2.entity.Product;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

@Builder
public class ProductSpecificationBuilder {

//    TODO: Access variable through meta mode classes
    public Specification<Product> hasQnty(long qnty) {
        return (root, query, cb) -> cb.equal(root.get("quantity"), qnty);
    }

    public  Specification<Product> hasPrice(long price) {
        return (root, query, cb) -> cb.equal(root.get("price"), price);
    }

//    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//    final CriteriaQuery cq = builder.createQuery();
//    final Root root = cq.from(Product.class);
//    final Predicate quantity = builder.equal(root.get("quantity"), qnty);
//    final Predicate productId = builder.equal(root.get("productId"), id);
//        cq.where(quantity, productId);
//    final TypedQuery<Product> query = entityManager.createQuery(cq);
//        return query.getSingleResult();
}
