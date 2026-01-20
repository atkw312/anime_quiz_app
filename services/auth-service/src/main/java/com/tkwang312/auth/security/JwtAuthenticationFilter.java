package com.tkwang312.auth.security;

import com.tkwang312.auth.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


// Goes through filter on each client request.
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    // chain of responsibility design pattern filterchain. List
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        // check jwt token
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        // "Bearer " is len 7
        jwt = authHeader.substring(7);
        username =  jwtService.extractUsername(jwt);
        //check if user is authenticated yet
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //get user details from database
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            //check if user is valid
            if (jwtService.isTokenValid(jwt, userDetails)) {
                //create object
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails ,
                        null,
                        userDetails.getAuthorities()
                );
                //enforce token with details of request
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //update auth token
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            //pass onto next filter
            filterChain.doFilter(request, response);
        }

    }
}
