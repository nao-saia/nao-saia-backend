package br.com.nao.saia.repository;

import br.com.nao.saia.model.State;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface StateRepository extends ReactiveMongoRepository<State, Integer> {
	
}
