package br.com.nao.saia.controller;

import br.com.nao.saia.model.User;
import br.com.nao.saia.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {

	private final UserService service;

	public UserController(UserService userService) {
		this.service = userService;
	}

	@GetMapping("/{id}")
	public Mono<User> findById(@PathVariable final UUID id) {
		return service.findById(id);
	}

}