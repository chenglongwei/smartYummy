package com.smartYummy.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by chenglongwei on 5/22/16.
 */
public class DispatchAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        Collection<GrantedAuthority> authCollection = (Collection<GrantedAuthority>) authentication.getAuthorities();
        HttpSession session = request.getSession();
        if (session == null) {
            return;
        }
        for (GrantedAuthority ga : authCollection) {
            if (ga.getAuthority().equals("CUSTOMER")) {
                response.sendRedirect("/");
                return;
            } else if (ga.getAuthority().equals("ADMIN")) {
                response.sendRedirect("/admin/index");
                return;
            }
        }

    }
}
