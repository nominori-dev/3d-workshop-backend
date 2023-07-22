package com.nominori.oms.application.product;

import com.nominori.oms.core.product.Product;

public interface ProductQueryService {

    Product findById(Long id);

}
