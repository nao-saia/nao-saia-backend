package br.com.nao.saia.repository;

import br.com.nao.saia.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserRepository extends ReactiveMongoRepository<User, UUID> {
	
	Mono<User> findByUsername(String username);
	
}
