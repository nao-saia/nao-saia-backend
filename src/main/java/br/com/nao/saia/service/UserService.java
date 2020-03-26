package br.com.nao.saia.service;

import br.com.nao.saia.exception.UserNotFoundException;
import br.com.nao.saia.model.User;
import br.com.nao.saia.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Mono<User> findById(final UUID id) {
		return userRepository.findById(id)
				.switchIfEmpty(Mono.error(new UserNotFoundException(id)));
	}

}
