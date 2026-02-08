package com.thearckay.ContactsBackend.infra.security;

import com.thearckay.ContactsBackend.entity.User;
import com.thearckay.ContactsBackend.repository.UserRepository;
import com.thearckay.ContactsBackend.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        if (token != null){
            System.out.println("FILTRO EXECUTOU");
            String email = tokenService.verifyToken(token);
            System.out.println("O email do meliante é:  "+email);
            if (email !=null && !email.isEmpty()){
                User userFromDataBase = userRepository.findByEmail(email);
                var authentication = new UsernamePasswordAuthenticationToken(userFromDataBase, null, userFromDataBase.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("Principal no filtro: " + authentication.getPrincipal());

            }
        }

        filterChain.doFilter(request,response);
    }

    private String getToken(HttpServletRequest request){
        var header = request.getHeader("Authorization");
        if (header == null) return null;
        return header.replace("Bearer ", "");
    }


}
