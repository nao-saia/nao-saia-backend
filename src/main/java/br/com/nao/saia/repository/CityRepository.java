package br.com.nao.saia.repository;

import br.com.nao.saia.model.City;
import br.com.nao.saia.model.State;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends ReactiveMongoRepository<City, Integer> {
	
	Flux<City> findByStateUf(String uf);
}
