package br.com.nao.saia.service;

import br.com.nao.saia.converter.UserConverter;
import br.com.nao.saia.dto.LoginDTO;
import br.com.nao.saia.dto.UserDTO;
import br.com.nao.saia.exception.PasswordInvalidException;
import br.com.nao.saia.exception.UserNotFoundException;
import br.com.nao.saia.model.User;
import br.com.nao.saia.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

/**
 * Service de {@link User}
 */
@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Mono<UserDTO> login(LoginDTO loginDTO) {
		return userRepository.findByEmail(loginDTO.getEmail())
				.map(UserConverter::fromDomainToDTO)
				.map(user -> {
					if (Objects.nonNull(user) && !user.getPassword().equals(loginDTO.getPassword())) {
						throw new PasswordInvalidException();
					}
					return user;
				})
				.switchIfEmpty(Mono.error(new UserNotFoundException(loginDTO.getEmail())));
	}

	public Mono<User> findById(UUID id) {
		return this.userRepository.findById(id).switchIfEmpty(Mono.error(new UserNotFoundException(id)));
	}

	public Mono<UserDTO> createUser(UserDTO userDTO) {
		return Mono.just(userDTO)
				.map(UserConverter::fromDTOToDomain)
				.flatMap(userToBeSaved -> userRepository.save(userToBeSaved)
						.then(Mono.just(UserConverter.fromDomainToDTO(userToBeSaved))));
	}

}
