package br.com.nao.saia.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.nao.saia.dto.ResponseDTO;
import br.com.nao.saia.exception.UserNotFoundException;
import br.com.nao.saia.model.User;
import br.com.nao.saia.repository.UserRepository;
import reactor.core.publisher.Mono;

/**
 * Service de {@link User}
 */
@Service
public class UserService {

	private final UserRepository repository;

	public UserService(UserRepository userRepository) {
		this.repository = userRepository;
	}

	public ResponseDTO login(User user) {
		Mono<User> userBd = repository.findByEmailAndPassword(user.getEmail(), user.getPassword());
		if (userBd != null) {
			// Implementar regra
		} else {
			// Implementar regra
			repository.save(user);
		}
		return new ResponseDTO();
	}

	public Mono<User> findById(UUID id) {
		return this.repository.findById(id).switchIfEmpty(Mono.error(new UserNotFoundException(id)));
	}

	public Mono<User> createUser(User user) {
		return this.repository.findByEmail(user.getEmail())
			.flatMap(foundUser -> Mono.just(new User()))
			.switchIfEmpty(Mono.defer(() -> this.repository.save(user)));
	}

}
