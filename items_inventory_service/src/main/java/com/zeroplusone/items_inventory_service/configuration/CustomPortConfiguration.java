package com.zeroplusone.items_inventory_service.configuration;


import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

import com.zeroplusone.items_inventory_service.configuration.utils.Constants;


//This class is to define additional connector for tomcat
//1. one for the internal api usage 
//2. second for external api usage

@Configuration
public class CustomPortConfiguration implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        Connector publicConnector = new Connector();
        publicConnector.setPort(Constants.PUBLIC_API_PORT);
        Connector internalConnector = new Connector();
        internalConnector.setPort(Constants.INTERNAL_API_PORT);
        factory.addAdditionalTomcatConnectors(internalConnector,publicConnector);
    }

}
