package br.com.nao.saia.security;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.nao.saia.security.Constants.AUTHORITIES_KEY;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @SuppressWarnings("unchecked")
	@Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        String username;
        try {
            username = jwtTokenUtil.getUsernameFromToken(authToken);
        } catch (Exception e) {
            username = null;
        }
        if (username != null && !jwtTokenUtil.isTokenExpired(authToken)) {
            Claims claims = jwtTokenUtil.getAllClaimsFromToken(authToken);
            List<String> roles = claims.get(AUTHORITIES_KEY, List.class);
            List<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, username, authorities);
            return Mono.just(auth);
        } else {
            return Mono.empty();
        }
    }
}
