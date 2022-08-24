package com.example.blogapi.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response
            , FilterChain filterChain
    ) throws ServletException, IOException {

        // 1. Get token
        String requestToken = request.getHeader("Authorization");

        String username = null;
        String token = null;
        //-> Example of token
        // requestTokent =Bearer 1dr4rkaff1%m35l39^4&.....
        if (requestToken != null && requestToken.startsWith("Bearer")) {
            token = requestToken.substring(7);
            try {
                username = jwtHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                System.out.println("JwtToken Expired");
            } catch (ExpiredJwtException e) {
                System.out.println("JwtToken expire");
            } catch (MalformedJwtException e) {
                System.out.println("invalid token");
            }

        } else
            System.out.println("Token not start with Bearer");

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (jwtHelper.validateToken(token, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                System.out.println("Invalid jwt token");
            }

        }else {
            System.out.println("Username is null or context is not null");
        }

        filterChain.doFilter(request,response);
    }
}
