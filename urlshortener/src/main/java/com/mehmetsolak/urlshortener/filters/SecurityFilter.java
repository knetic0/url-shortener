package com.mehmetsolak.urlshortener.filters;

import com.mehmetsolak.urlshortener.constants.Prefix;
import com.mehmetsolak.urlshortener.constants.RequestHeaderFieldName;
import com.mehmetsolak.urlshortener.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UserDetailsService userDetailsService;

    public SecurityFilter(TokenService tokenService, UserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(RequestHeaderFieldName.AUTHORIZATION);

        if(authorizationHeader == null || !authorizationHeader.startsWith(Prefix.BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authorizationHeader.substring(Prefix.BEARER.length());
        final String username = tokenService.extractUsername(token);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(username != null && authentication == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(tokenService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                request.setAttribute("user", userDetails);
            }
        }

        filterChain.doFilter(request, response);
    }
}
