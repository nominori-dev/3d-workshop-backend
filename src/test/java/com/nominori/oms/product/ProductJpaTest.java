package com.nominori.oms.product;

import com.nominori.oms.product.model.Product;
import com.nominori.oms.product.model.Type;
import com.nominori.oms.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
class ProductJpaTest {

    private final ProductRepository productRepository;

    @Test
    public void should_store_a_product_with_type(){

        Set<Type> productTypes = new HashSet<>();

        productTypes.add(new Type("Model", "3D Model"));

        Product product = productRepository.save(new Product("Asset", "Description", productTypes));

        log.info(product.toString());

    }

}
