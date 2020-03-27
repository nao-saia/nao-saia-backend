package br.com.nao.saia.service;

import br.com.nao.saia.converter.MerchantConverter;
import br.com.nao.saia.dto.MerchantDTO;
import br.com.nao.saia.exception.BusinessException;
import br.com.nao.saia.exception.MerchantNotFoundException;
import br.com.nao.saia.model.Address;
import br.com.nao.saia.model.Merchant;
import br.com.nao.saia.repository.MerchantRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@Service
public class MerchantService {

    private final MerchantRepository merchantRepository;

    public MerchantService(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    public Mono<MerchantDTO> findById(final UUID id) {
        return merchantRepository.findById(id)
                .map(MerchantConverter::fromDomainToDTO)
                .switchIfEmpty(Mono.error(new MerchantNotFoundException(id)));
    }

    public Mono<PageSupport<MerchantDTO>> findByFilter(final String fantasyName,
                                                       final String category,
                                                       final String city,
                                                       final String state,
                                                       final Double latitude,
                                                       final Double longitude,
                                                       final Double distance,
                                                       final Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("fantasyName", contains().ignoreCase())
                .withMatcher("categories", contains().ignoreCase());

        Merchant merchant = buildMerchantForQuery(fantasyName, category, city, state);

        Example<Merchant> example = Example.of(merchant, matcher);

        if (Objects.nonNull(latitude) && Objects.nonNull(longitude)) {
            return findByLocation(latitude, longitude, distance, pageable);
        }

        return merchantRepository.findAll(example)
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

    public Mono<PageSupport<MerchantDTO>> findByUserId(final UUID userId, final Pageable pageable) {
        return merchantRepository.findByUserId(userId)
                .collectList()
                .map(list -> new PageSupport<>(
                        list
                                .stream()
                                .map(MerchantConverter::fromDomainToDTO)
                                .collect(Collectors.toList()),
                        pageable.getPageNumber(), pageable.getPageSize(), list.size()));
    }

    public Mono<MerchantDTO> patch(UUID id, MerchantDTO merchantDTO) {
        return merchantRepository.findById(id)
                .switchIfEmpty(Mono.error(new MerchantNotFoundException(id)))
                .map(merchant -> MerchantConverter.update(merchant, merchantDTO))
                .flatMap(merchantToBeSaved -> merchantRepository.save(merchantToBeSaved)
                        .flatMap(merchantSaved -> Mono.just(MerchantConverter.fromDomainToDTO(merchantSaved)))
                );
    }

    public Mono<MerchantDTO> update(MerchantDTO merchantDTO) {
        return merchantRepository.findById(merchantDTO.getId())
                .switchIfEmpty(Mono.error(new MerchantNotFoundException(merchantDTO.getId())))
                .then(Mono.just(merchantDTO))
                .map(MerchantConverter::fromDTOToDomain)
                .flatMap(merchantToBeSaved -> merchantRepository.save(merchantToBeSaved)
                        .flatMap(merchantSaved -> Mono.just(MerchantConverter.fromDomainToDTO(merchantSaved)))
                );
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

    public Mono<MerchantDTO> deleteById(final UUID id) {
        return merchantRepository.findById(id)
                .switchIfEmpty(Mono.empty())
                .filter(Objects::nonNull)
                .flatMap(productToBeDeleted -> merchantRepository.delete(productToBeDeleted)
                        .then(Mono.just(MerchantConverter.fromDomainToDTO(productToBeDeleted))));
    }

    private Merchant buildMerchantForQuery(final String fantasyName,
                                           final String category,
                                           final String city,
                                           final String state) {
        Merchant merchant = new Merchant();
        Optional.ofNullable(fantasyName).ifPresent(merchant::setFantasyName);
        Optional.ofNullable(category).ifPresent(c -> merchant.setCategories(Collections.singletonList(c)));
        Optional.ofNullable(state).ifPresent(s -> {
            Address address = new Address();
            address.setState(s);
            merchant.setAddress(address);
        });
        Optional.ofNullable(city).ifPresent(c -> {
            Address address = new Address();
            address.setCity(c);
            merchant.setAddress(address);
        });
        Optional.ofNullable(city).ifPresent(c -> {
            Address address = new Address();
            address.setCity(c);
            merchant.setAddress(address);
        });
        return merchant;
    }

}
