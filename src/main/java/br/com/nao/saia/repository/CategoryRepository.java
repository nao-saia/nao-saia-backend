package br.com.nao.saia.repository;

import br.com.nao.saia.model.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface CategoryRepository extends ReactiveMongoRepository<Category, UUID> {
	
}
