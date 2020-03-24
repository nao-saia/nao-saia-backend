package br.com.nao.saia.service;

import br.com.nao.saia.dto.ResponseDTO;
import br.com.nao.saia.exception.BusinessException;
import br.com.nao.saia.exception.UserNotFoundException;
import br.com.nao.saia.model.User;
import br.com.nao.saia.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

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

    public ResponseDTO<Mono<User>> createUser(User user) {
        Mono<User> userBd = repository.findByEmail(user.getEmail());
        if (userBd != null) {
            throw new BusinessException("Usuário ja cadastrado");
        }
        Mono<User> saved = this.repository.save(user);
        return ResponseDTO.success(saved);
    }

}
