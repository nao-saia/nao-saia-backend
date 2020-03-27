package br.com.nao.saia.config;

import br.com.nao.saia.security.AuthenticationManager;
import br.com.nao.saia.security.SecurityContextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private static final String ADMIN = "ADMIN";
    private static final String OWNER = "OWNER";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SecurityContextRepository securityContextRepository;

    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
        String[] patterns = new String[]{
                "/auth/**",
                "/cities/**",
                "/states/**",
                "/geolocation/**"
        };
        return http.cors()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((swe, e) -> Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED)
                )).accessDeniedHandler((swe, e) -> Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN)))
                .and()
                .csrf().disable()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers(HttpMethod.GET, "/categories/**").permitAll()
                .pathMatchers(HttpMethod.POST, "/categories").hasRole(ADMIN)
                .pathMatchers(HttpMethod.GET, "/contributors/**").permitAll()
                .pathMatchers(HttpMethod.PATCH, "/contributors/**").hasRole(ADMIN)
                .pathMatchers(HttpMethod.PUT, "/contributors").hasRole(ADMIN)
                .pathMatchers(HttpMethod.POST, "/contributors").hasRole(ADMIN)
                .pathMatchers(HttpMethod.GET, "/merchants/owner/**").hasAnyRole(ADMIN, OWNER)
                .pathMatchers(HttpMethod.GET, "/merchants/**").permitAll()
                .pathMatchers(HttpMethod.POST, "/merchants").hasAnyRole(ADMIN, OWNER)
                .pathMatchers(HttpMethod.PUT, "/merchants").hasAnyRole(ADMIN, OWNER)
                .pathMatchers(HttpMethod.PATCH, "/merchants").hasAnyRole(ADMIN, OWNER)
                .pathMatchers(HttpMethod.DELETE, "/merchants").hasAnyRole(ADMIN, OWNER)
                .pathMatchers(patterns).permitAll()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .anyExchange().authenticated()
                .and()
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

