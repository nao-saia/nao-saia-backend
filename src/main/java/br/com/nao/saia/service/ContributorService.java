package br.com.nao.saia.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.nao.saia.exception.CategoryNotFoundException;
import br.com.nao.saia.model.Contributor;
import br.com.nao.saia.repository.ContributorRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ContributorService {

	private final ContributorRepository repository;

	public ContributorService(ContributorRepository repository) {
		this.repository = repository;
	}

	public Flux<Contributor> findAll() {
		return repository.findAll();
	}
	
	public Mono<Contributor> findById(final UUID id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new CategoryNotFoundException(id)));
    }


    public Mono<Contributor> save(final Contributor contributor) {
        return Mono.just(contributor)
                .flatMap(contributorToBeSaved -> repository.save(contributorToBeSaved)
                        .then(Mono.just(contributorToBeSaved)));
    }

}
