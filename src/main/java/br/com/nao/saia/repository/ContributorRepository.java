package br.com.nao.saia.repository;

import br.com.nao.saia.model.Contributor;
import br.com.nao.saia.model.State;

import java.util.UUID;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ContributorRepository extends ReactiveMongoRepository<Contributor, UUID> {
	
}
