package br.com.nao.saia.repository;

import br.com.nao.saia.model.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CategoryRepository extends ReactiveMongoRepository<Category, UUID> {

    Mono<Category> findByName(String name);
}
