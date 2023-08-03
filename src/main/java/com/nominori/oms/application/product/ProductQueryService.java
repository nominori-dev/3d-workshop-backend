package com.nominori.oms.application.product;

import com.nominori.oms.core.product.Product;
import org.springframework.data.domain.Page;

public interface ProductQueryService {

    Product findById(Long id);
    Product findByName(String name);
    Page<Product> findAll(QueryProductParam param);

}
