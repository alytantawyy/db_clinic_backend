package com.db_clinic.db_clinic.configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)

public class SimpleCorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException { 
                HttpServletRequest request = (HttpServletRequest) req;
                HttpServletResponse response = (HttpServletResponse) res;

                Map<String, String> map  = new HashMap<>();
                String originHeader = request.getHeader("origin");
        
                response.setHeader("Access-Control-Allow-Origin", originHeader);
                response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
                response.setHeader("Access-Control-Max-Age", "3600");
                response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization");
                response.setHeader("Access-Control-Allow-Credentials", "*");
        
                if("OPTIONS".equalsIgnoreCase(request.getMethod())){
                    response.setStatus(HttpServletResponse.SC_OK);
                }
                else{
                    chain.doFilter(req, res);
                }
            }
        
            @Override
            public void init(FilterConfig filterConfig)  {
                
            }
        
            

}