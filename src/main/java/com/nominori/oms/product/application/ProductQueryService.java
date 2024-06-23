package com.nominori.oms.product.application;

import com.nominori.oms.product.domain.Product;
import org.springframework.data.domain.Page;

public interface ProductQueryService {

    Product findById(Long id);
    Product findByName(String name);
    Page<Product> findAll(QueryProductParam param);

}
