package com.nominori.oms.application.product.type;

import com.nominori.oms.core.product.Type;

import java.util.Optional;

public interface TypeQueryService {
    Type findById(Long id);
    Type findByName(String name);
}
