package com.nominori.oms.security.util;

public class RoleConverter {

    public static String convert(String source){
        return source.toUpperCase().replace("-", "_");
    }

}
