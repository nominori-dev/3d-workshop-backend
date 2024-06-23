package com.nominori.oms.product.application.type;

import com.nominori.oms.product.domain.Type;

public interface TypeService {
    Type addType(Type type);
    Type updateType(Long id, TypeParam param);
    void removeType(Long id);
}
