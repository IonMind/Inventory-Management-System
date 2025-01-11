package com.zeroplusone.items_inventory_service.configuration.utils;



public final class Constants {
    public static final Integer INTERNAL_API_PORT = Integer.valueOf(System.getenv().getOrDefault("INTERNAL_API_PORT", "9000"));

  
    public static Integer PUBLIC_API_PORT=Integer.valueOf(System.getenv().getOrDefault("PUBLIC_API_PORT", "8002"));;

    public static final String PUBLIC_API_BASE_PATH = "/api/v1/item";
    public static final String INTERNAL_API_BASE_PATH = "/internal";
}
