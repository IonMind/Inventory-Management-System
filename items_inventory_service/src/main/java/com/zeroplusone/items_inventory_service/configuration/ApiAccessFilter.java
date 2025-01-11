package com.zeroplusone.items_inventory_service.configuration;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;

import com.zeroplusone.items_inventory_service.configuration.utils.Constants;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Filter external and internal apis
@Configuration
public class ApiAccessFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        Boolean isInternalApiCall = httpRequest.getRequestURI().startsWith(Constants.INTERNAL_API_BASE_PATH);
        Boolean isInternalPortCall = httpRequest.getLocalPort() == Constants.INTERNAL_API_PORT;

        if (isInternalApiCall && isInternalPortCall) {
            filterChain.doFilter(httpRequest, httpResponse);
            return;
        }
        Boolean isExternalApiCall = httpRequest.getRequestURI().startsWith(Constants.PUBLIC_API_BASE_PATH);
        Boolean isExternalPortCall = httpRequest.getLocalPort() == Constants.PUBLIC_API_PORT;

        if (isExternalApiCall && isExternalPortCall) {
            filterChain.doFilter(httpRequest, httpResponse);
            return;
        }
        httpResponse.sendError(404);
    }

}
