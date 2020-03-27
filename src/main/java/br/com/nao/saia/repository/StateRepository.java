package br.com.nao.saia.repository;

import br.com.nao.saia.model.State;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface StateRepository extends ReactiveMongoRepository<State, Integer> {

    Flux<State> findAllByOrderByNameAsc();
	
}
