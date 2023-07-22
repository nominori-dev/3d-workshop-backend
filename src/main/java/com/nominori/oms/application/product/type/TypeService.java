package com.nominori.oms.application.product.type;

import com.nominori.oms.core.product.Type;

public interface TypeService {
    Type addType(NewTypeParam param);
    Type updateType(Long id);
    void removeType(Long id);
}
