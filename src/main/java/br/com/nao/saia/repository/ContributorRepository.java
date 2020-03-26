package br.com.nao.saia.repository;

import br.com.nao.saia.model.Contributor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ContributorRepository extends ReactiveMongoRepository<Contributor, UUID> {

    Mono<Contributor> findByName(String name);

}
