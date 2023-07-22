package com.nominori.oms.application.product;

import com.nominori.oms.core.product.Product;

public interface ProductService {

    Product addProduct(Product product);
    Product updateProduct(Product product, UpdateProductParam updateProductParam);
    void removeProduct(Product product);

}
