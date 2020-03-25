package br.com.nao.saia.controller;

import br.com.nao.saia.model.User;
import br.com.nao.saia.security.model.AuthRequest;
import br.com.nao.saia.security.model.AuthResponse;
import br.com.nao.saia.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public Mono<AuthResponse> login(@Valid @RequestBody final AuthRequest authRequest) {
        return authService.login(authRequest);
    }

    @PostMapping("signup")
    public Mono<User> signUp(@Valid @RequestBody final User user) {
        return authService.signUp(user);
    }

}
