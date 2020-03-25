package br.com.nao.saia.controller;

import br.com.nao.saia.dto.LoginDTO;
import br.com.nao.saia.dto.UserDTO;
import br.com.nao.saia.model.User;
import br.com.nao.saia.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

/**
 * Classe que armazena os endpoints de {@link User} recebendo as requisicoes,
 * tratando e devolvendo os resultados
 *
 * @author Taynan Rezende
 * @since 22/03/2020
 */
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

	@PostMapping("login")
	public Mono<UserDTO> login(@Valid @RequestBody final LoginDTO loginDTO) {
		return service.login(loginDTO);
	}

	@PostMapping
	public Mono<UserDTO> createUser(@Valid @RequestBody final UserDTO userDTO) {
		return this.service.createUser(userDTO);
	}

}