package com.nominori.oms.product.api.model;

import com.nominori.oms.product.api.ProductController;
import com.nominori.oms.product.domain.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ProductModelAssembler extends RepresentationModelAssemblerSupport<Product, ProductModel> {

    public ProductModelAssembler() {
        super(ProductController.class, ProductModel.class);
    }

    @Override
    public ProductModel toModel(Product entity) {
        ProductModel model = new ProductModel();
        BeanUtils.copyProperties(entity, model);
        return model;
    }
}
