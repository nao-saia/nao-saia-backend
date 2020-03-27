package br.com.nao.saia.service;

import br.com.nao.saia.converter.ContributorConverter;
import br.com.nao.saia.dto.ContributorDTO;
import br.com.nao.saia.exception.BusinessException;
import br.com.nao.saia.exception.ContributorNotFoundException;
import br.com.nao.saia.repository.ContributorRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ContributorService {

    private final ContributorRepository contributorRepository;

    public ContributorService(ContributorRepository contributorRepository) {
        this.contributorRepository = contributorRepository;
    }

    public Flux<ContributorDTO> findAll() {
        return contributorRepository.findAll()
                .map(ContributorConverter::fromDomainToDTO);
    }

    public Mono<ContributorDTO> findById(final UUID id) {
        return contributorRepository.findById(id)
                .map(ContributorConverter::fromDomainToDTO)
                .switchIfEmpty(Mono.error(new ContributorNotFoundException(id)));
    }

    public Mono<ContributorDTO> patch(UUID id, ContributorDTO contributorDTO) {
        return contributorRepository.findById(id)
                .switchIfEmpty(Mono.error(new ContributorNotFoundException(id)))
                .map(contributor -> ContributorConverter.update(contributor, contributorDTO))
                .flatMap(contributorToBeSaved -> contributorRepository.save(contributorToBeSaved)
                        .flatMap(contributorSaved -> Mono.just(ContributorConverter.fromDomainToDTO(contributorSaved)))
                );
    }

    public Mono<ContributorDTO> update(ContributorDTO contributorDTO) {
        return contributorRepository.findById(contributorDTO.getId())
                .switchIfEmpty(Mono.error(new ContributorNotFoundException(contributorDTO.getId())))
                .then(Mono.just(contributorDTO))
                .map(ContributorConverter::fromDTOToDomain)
                .flatMap(contributorToBeSaved -> contributorRepository.save(contributorToBeSaved)
                        .flatMap(contributorSaved -> Mono.just(ContributorConverter.fromDomainToDTO(contributorSaved)))
                );
    }

    public Mono<ContributorDTO> save(final ContributorDTO contributorDTO) {
        return contributorRepository.findByName(contributorDTO.getName())
                .flatMap(contributor -> Mono.error(new BusinessException("Contribuidor já cadastrado")))
                .switchIfEmpty(Mono.just(contributorDTO)
                        .map(ContributorConverter::fromDTOToDomain)
                        .flatMap(contributorToBeSaved -> contributorRepository.save(contributorToBeSaved)
                                .flatMap(contributorSaved -> Mono.just(ContributorConverter.fromDomainToDTO(contributorSaved)))
                        ))
                .cast(ContributorDTO.class);
    }
}
