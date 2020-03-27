package br.com.nao.saia.repository;

import br.com.nao.saia.model.City;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CityRepository extends ReactiveMongoRepository<City, Integer> {
	
	Flux<City> findByStateUfOrderByNameAsc(String uf);
}
