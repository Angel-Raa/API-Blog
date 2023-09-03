package org.caja.idea.configuration.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.caja.idea.configuration.jwt.JwtService;
import org.caja.idea.entity.models.Users;
import org.caja.idea.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
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
        // Extraer el token del header
        String jwtToken = header.replace("Bearer ", "");

        // Validar y obtener el nombre de usuario de token JWT
        String username = service.extractUsername(jwtToken);
        Optional<Users> users = repository.findByUsername(username);
        if (users.isPresent()) {
            Users user = users.get();
            // Crear el objeto de autenticación
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
            // Configurar la autenticación en el contexto de spring security
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        // Continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }
}