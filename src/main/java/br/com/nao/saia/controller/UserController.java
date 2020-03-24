package br.com.nao.saia.controller;

import br.com.nao.saia.dto.ResponseDTO;
import br.com.nao.saia.model.User;
import br.com.nao.saia.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

	private static final String JSON = MediaType.APPLICATION_JSON_VALUE;

	private final UserService service;

	public UserController(UserService userService) {
		this.service = userService;
	}

	@SuppressWarnings("rawtypes")
	@PostMapping(path = "/login", consumes = JSON, produces = JSON)
	public ResponseEntity<ResponseDTO> login(@Valid @RequestBody User user) {
		return ResponseEntity.ok(service.login(user));
	}

	@PostMapping(consumes = JSON, produces = JSON)
	public Mono<ResponseEntity<?>> createUser(@Valid @RequestBody User user) {
		Mono<User> userCreated = this.service.createUser(user);
		return userCreated.flatMap(foundUser -> {
			if (foundUser.getId() != null) {
				return Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(ResponseDTO.success(foundUser)));
			}
			return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já cadastrado"));
		});

	}

	@GetMapping("/{id}")
	public Mono<User> findById(@PathVariable UUID id) {
		return service.findById(id);
	}

}