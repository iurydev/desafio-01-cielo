package com.example.demo.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class TipoClienteInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();
        if (path.startsWith("/cliente/pf")) {
            request.setAttribute("tipoCliente", "PF");
        } else if (path.startsWith("/cliente/pj")) {
            request.setAttribute("tipoCliente", "PJ");
        }
        return true;
    }
}
