package org.caja.idea.configuration.security;

import org.caja.idea.configuration.filter.JwtAuthenticationEntryPoint;
import org.caja.idea.configuration.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfiguration {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(AbstractHttpConfigurer::disable)
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(httpSecurityExceptionHandlingConfigure -> httpSecurityExceptionHandlingConfigure.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .authorizeHttpRequests(getAuthorizationManagerRequestMatcherRegistryCustomizer());
        return http.build();
    }

    private static Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> getAuthorizationManagerRequestMatcherRegistryCustomizer() {
        return (api) -> {
            api.requestMatchers("/error").permitAll();
            api.requestMatchers(HttpMethod.POST, "/authentication/signup").permitAll();
            api.requestMatchers(HttpMethod.POST, "/authentication/login").permitAll();
            api.requestMatchers(HttpMethod.GET, "/authentication/all").permitAll();
            api.requestMatchers(HttpMethod.GET, "/post/all").permitAll();
            api.requestMatchers(HttpMethod.GET, "/post/{id}").permitAll();
            api.requestMatchers(HttpMethod.GET, "/post/find/{title}").permitAll();
            api.requestMatchers(HttpMethod.POST, "/post/create").permitAll();
            api.anyRequest().denyAll();
        };
    }

}
