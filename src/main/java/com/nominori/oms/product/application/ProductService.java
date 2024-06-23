package com.nominori.oms.product.application;

import com.nominori.oms.product.domain.Product;

public interface ProductService {

    Product addProduct(Product product);
    Product updateProduct(Product product, UpdateProductParam updateProductParam);
    void removeProduct(Product product);

}
