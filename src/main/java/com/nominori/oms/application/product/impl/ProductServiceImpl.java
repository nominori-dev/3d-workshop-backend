package com.nominori.oms.application.product.impl;

import com.nominori.oms.application.product.ProductService;
import com.nominori.oms.application.product.UpdateProductParam;
import com.nominori.oms.core.product.Product;
import com.nominori.oms.core.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;



    //#TODO Unique name constraint check
    @Override
    public Product addProduct(Product product) {




        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product, UpdateProductParam updateProductParam) {
        product.update(updateProductParam.getName(), updateProductParam.getDescription());
        return productRepository.save(product);
    }

    @Override
    public void removeProduct(Product product) {
        productRepository.delete(product);
    }
}
