package br.com.nao.saia.service;

import br.com.nao.saia.converter.MerchantConverter;
import br.com.nao.saia.dto.MerchantDTO;
import br.com.nao.saia.exception.BusinessException;
import br.com.nao.saia.exception.MerchantNotFoundException;
import br.com.nao.saia.repository.MerchantRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MerchantService {

    private final MerchantRepository merchantRepository;

    public MerchantService(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    public Flux<MerchantDTO> findAll() {
        return merchantRepository.findAll()
                .map(MerchantConverter::fromDomainToDTO);
    }

    public Mono<MerchantDTO> findById(final UUID id) {
        return merchantRepository.findById(id)
                .map(MerchantConverter::fromDomainToDTO)
                .switchIfEmpty(Mono.error(new MerchantNotFoundException(id)));
    }

    public Mono<PageSupport<MerchantDTO>> findByCategory(final String category, final Pageable pageable) {
        return merchantRepository.findByCategoriesIn(category)
                .collectList()
                .map(list -> new PageSupport<>(
                        list
                                .stream()
                                .map(MerchantConverter::fromDomainToDTO)
                                .collect(Collectors.toList()),
                        pageable.getPageNumber(), pageable.getPageSize(), list.size()));
    }

    public Mono<PageSupport<MerchantDTO>> findByCity(final String city, final Pageable pageable) {
        return merchantRepository.findByAddressCity(city)
                .collectList()
                .map(list -> new PageSupport<>(
                        list
                                .stream()
                                .map(MerchantConverter::fromDomainToDTO)
                                .collect(Collectors.toList()),
                        pageable.getPageNumber(), pageable.getPageSize(), list.size()));
    }

    public Mono<PageSupport<MerchantDTO>> findByState(final String state, final Pageable pageable) {
        return merchantRepository.findByAddressState(state)
                .collectList()
                .map(list -> new PageSupport<>(
                        list
                                .stream()
                                .map(MerchantConverter::fromDomainToDTO)
                                .collect(Collectors.toList()),
                        pageable.getPageNumber(), pageable.getPageSize(), list.size()));
    }

    public Mono<PageSupport<MerchantDTO>> findByLocation(final double latitude, final double longitude, final double distance, final Pageable pageable) {
        return merchantRepository.findByAddressLocationNear(
                new Point(latitude, longitude),
                new Distance(distance, Metrics.KILOMETERS))
                .collectList()
                .map(list -> new PageSupport<>(
                        list
                            .stream()
                            .map(MerchantConverter::fromDomainToDTO)
                            .collect(Collectors.toList()),
                        pageable.getPageNumber(), pageable.getPageSize(), list.size()));
    }

    public Mono<MerchantDTO> save(final MerchantDTO merchantDTO) {
        return merchantRepository.findByCnpj(merchantDTO.getCnpj())
                .flatMap(merchant -> Mono.error(new BusinessException("Estabelecimento já cadastrado")))
                .switchIfEmpty(Mono.just(merchantDTO)
                        .map(MerchantConverter::fromDTOToDomain)
                        .flatMap(merchantToBeSaved -> merchantRepository.save(merchantToBeSaved)
                                .flatMap(merchantSaved -> Mono.just(MerchantConverter.fromDomainToDTO(merchantSaved)))
                        ))
                .cast(MerchantDTO.class);
    }

    public Mono<MerchantDTO> update(UUID id, MerchantDTO merchantDTO) {
        return merchantRepository.findById(id)
                .switchIfEmpty(Mono.error(new MerchantNotFoundException(id)))
                .map(merchant -> MerchantConverter.update(merchant, merchantDTO))
                .flatMap(merchantToBeSaved -> merchantRepository.save(merchantToBeSaved)
                        .flatMap(merchantSaved -> Mono.just(MerchantConverter.fromDomainToDTO(merchantSaved)))
                );
    }

    public Mono<MerchantDTO> deleteById(final UUID id) {
        return merchantRepository.findById(id)
                .switchIfEmpty(Mono.empty())
                .filter(Objects::nonNull)
                .flatMap(productToBeDeleted -> merchantRepository.delete(productToBeDeleted)
                        .then(Mono.just(MerchantConverter.fromDomainToDTO(productToBeDeleted))));
    }

}
