package com.example.recipe.core.filters;


import com.example.recipe.core.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String jwtHeader=request.getHeader("Authorization");
        if(jwtHeader != null && jwtHeader.startsWith("Bearer ")){
            String jwt=jwtHeader.substring(7);
            if(jwtService.validateToken(jwt)){
                String userName=jwtService.extractUserName(jwt);
                String userRole=jwtService.extractUserRole(jwt);
                UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(userName,null, AuthorityUtils.createAuthorityList(userRole));
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }
        filterChain.doFilter(request,response);
    }
}

