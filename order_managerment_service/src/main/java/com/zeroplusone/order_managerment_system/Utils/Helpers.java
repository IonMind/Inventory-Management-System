package com.zeroplusone.order_managerment_system.Utils;

import org.springframework.web.client.RestClient;

public final class Helpers {
    // public final static String ITEMS_API_BASEURL = "http://localhost:8002";
    public final static RestClient restClient = RestClient.builder().baseUrl("http://localhost:8002/api/v1")
                .build();
}
