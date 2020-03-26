package br.com.nao.saia.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.nao.saia.exception.BusinessException;
import br.com.nao.saia.model.User;
import br.com.nao.saia.repository.UserRepository;
import br.com.nao.saia.security.JwtTokenUtil;
import br.com.nao.saia.security.model.AuthRequest;
import br.com.nao.saia.security.model.AuthResponse;
import reactor.core.publisher.Mono;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtTokenUtil jwtTokenUtil, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public Mono<AuthResponse> login(AuthRequest loginRequest) {
        return userRepository.findByUsername(loginRequest.getUsername())
                .map(user -> {
                    if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                        return new AuthResponse(jwtTokenUtil.generateToken(user), user);
                    }
                    throw new BusinessException("Senha incorreta");
                })
                .switchIfEmpty(Mono.error(new BusinessException("Usuário não existe")));
    }

    public Mono<User> signUp(User user) {
        return userRepository.findByUsername(user.getUsername())
                .flatMap(u -> Mono.error(new BusinessException("Usuário já cadastrado")))
                .switchIfEmpty(Mono.just(user)
                        .map(u -> {
                        	u.setEnabled(true);
                        	u.setCreatedAt(LocalDateTime.now());
                            u.setPassword(passwordEncoder.encode(u.getPassword()));
                            return u;
                        })
                        .flatMap(userRepository::save)
                        )
                .cast(User.class);
    }

}
