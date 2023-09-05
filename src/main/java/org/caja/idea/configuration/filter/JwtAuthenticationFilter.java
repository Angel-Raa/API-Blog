package org.caja.idea.configuration.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.caja.idea.configuration.jwt.JwtService;
import org.caja.idea.entity.models.Users;
import org.caja.idea.exception.ExpiredJwtException;
import org.caja.idea.exception.InvalidTokenException;
import org.caja.idea.repository.IUserRepository;
import org.caja.idea.utils.constants.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private  IUserRepository repository;
    @Autowired
    private  JwtService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Obtener el header de Authorization
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            // Extra el token del header
            String jwtToken = header.split(" ")[1];

            // Validate the token
            if(!service.isTokenValid(jwtToken)) {
                throw new InvalidTokenException(Message.INVALID_TOKEN,401, HttpStatus.UNAUTHORIZED, LocalDateTime.now());
            }
            // Validator y obtener el nombre de usuario de token JWT
            String username = service.extractUsername(jwtToken);
            Optional<Users> users = repository.findByUsername(username);
            if (users.isPresent()) {
                Users user = users.get();
                // Carer el objet de authenticate
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
                // Configure la authenticate en el contexto de spring security
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            // Continual con la cadenza de filtrates
            filterChain.doFilter(request, response);
        }catch (ExpiredJwtException error){
            throw new InvalidTokenException(Message.EXPIRED_TOKEN,401, HttpStatus.UNAUTHORIZED, LocalDateTime.now());
        }
    }
}


