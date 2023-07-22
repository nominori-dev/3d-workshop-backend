package com.nominori.oms.application.product.type;

import com.nominori.oms.core.product.Type;

import java.util.Optional;

public interface TypeQueryService {
    Optional<Type> findById(Long id);
}
