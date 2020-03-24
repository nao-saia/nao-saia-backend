package br.com.nao.saia.repository;

import br.com.nao.saia.model.State;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends ReactiveMongoRepository<State, Integer> {
	
}
