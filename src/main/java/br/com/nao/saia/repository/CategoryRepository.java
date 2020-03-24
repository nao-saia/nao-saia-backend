package br.com.nao.saia.repository;

import br.com.nao.saia.model.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends ReactiveMongoRepository<Category, UUID> {
	
}
