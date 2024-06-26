package com.nominori.oms.product.repository;

import com.nominori.oms.product.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

    Optional<Type> findByName(String name);

    boolean existsByName(String name);
}
