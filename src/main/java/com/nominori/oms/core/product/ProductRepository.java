package com.nominori.oms.core.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    Page<Product> findByNameContaining(String name, Pageable pageable);
    Page<Product> findAllByProductTypeIn(Set<Type> productTypes, Pageable pageable);
    boolean existsByName(String name);
}
