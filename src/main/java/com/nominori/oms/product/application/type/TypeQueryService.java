package com.nominori.oms.product.application.type;

import com.nominori.oms.product.domain.Type;

public interface TypeQueryService {
    Type findById(Long id);
    Type findByName(String name);
}
