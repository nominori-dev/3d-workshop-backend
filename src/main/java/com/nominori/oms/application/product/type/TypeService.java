package com.nominori.oms.application.product.type;

import com.nominori.oms.core.product.Type;

public interface TypeService {
    Type addType(Type type);
    Type updateType(Long id, TypeParam param);
    void removeType(Long id);
}
