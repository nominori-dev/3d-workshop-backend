package com.nominori.oms.infrastructure.security.util;

public class RoleConverter {

    public static String convert(String source){
        return source.toUpperCase().replace("-", "_");
    }

}
