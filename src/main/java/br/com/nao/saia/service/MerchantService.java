package br.com.nao.saia.service;

import br.com.nao.saia.converter.MerchantConverter;
import br.com.nao.saia.dto.MerchantDTO;
import br.com.nao.saia.exception.MerchantNotFoundException;
import br.com.nao.saia.model.Merchant;
import br.com.nao.saia.repository.MerchantRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service de {@link Merchant}
 *
 * @author Taynan Rezende
 * @since 22/03/2020
 */
@Service
public class MerchantService {

    private final MerchantRepository merchantRepository;
    private final MerchantConverter merchantConverter;

    public MerchantService(MerchantRepository merchantRepository, MerchantConverter merchantConverter) {
        this.merchantRepository = merchantRepository;
        this.merchantConverter = merchantConverter;
    }

    public Mono<MerchantDTO> findById(UUID id) {
        return merchantRepository.findById(id)
                .map(merchantConverter::fromDomainToDTO)
                .switchIfEmpty(Mono.error(new MerchantNotFoundException(id)));
    }

    public Flux<MerchantDTO> findAll() {
        return merchantRepository.findAll()
                .map(merchantConverter::fromDomainToDTO);
    }

    public void save(MerchantDTO merchantDTO) {
        Merchant merchant = merchantConverter.fromDTOToDomain(merchantDTO);
        merchantRepository.save(merchant);
    }

    public Mono<Void> deleteById(UUID id) {
        return merchantRepository.findById(id)
                .flatMap(merchantRepository::delete);
    }

    public Mono<PageSupport<MerchantDTO>> findByCategory(String category, Pageable pageable) {
        return merchantRepository.findByCategoriesIn(category)
                .collectList()
                .map(list -> new PageSupport<>(
                        list
                                .stream()
                                .map(merchantConverter::fromDomainToDTO)
                                .collect(Collectors.toList()),
                        pageable.getPageNumber(), pageable.getPageSize(), list.size()));
    }

    public Mono<PageSupport<MerchantDTO>> findByCity(String city, Pageable pageable) {
        return merchantRepository.findByAddressCity(city)
                .collectList()
                .map(list -> new PageSupport<>(
                        list
                                .stream()
                                .map(merchantConverter::fromDomainToDTO)
                                .collect(Collectors.toList()),
                        pageable.getPageNumber(), pageable.getPageSize(), list.size()));
    }

    public Mono<PageSupport<MerchantDTO>> findByState(String state, Pageable pageable) {
        return merchantRepository.findByAddressState(state)
                .collectList()
                .map(list -> new PageSupport<>(
                        list
                                .stream()
                                .map(merchantConverter::fromDomainToDTO)
                                .collect(Collectors.toList()),
                        pageable.getPageNumber(), pageable.getPageSize(), list.size()));
    }

    /**
     * Para funfar tem que habilitar o geoNear no mongoDb.
     * https://drissamri.be/blog/2015/08/18/build-a-location-api-with-spring-data-mongodb-and-geojson/
     * @return
     */
    public Mono<PageSupport<MerchantDTO>> findByLocation(double latitude, double longitude, double distance, Pageable pageable) {
        return merchantRepository.findByAddressLocationNear(
                new Point(latitude, longitude),
                new Distance(distance, Metrics.KILOMETERS))
                .collectList()
                .map(list -> new PageSupport<>(
                        list
                            .stream()
                            .map(merchantConverter::fromDomainToDTO)
                            .collect(Collectors.toList()),
                        pageable.getPageNumber(), pageable.getPageSize(), list.size()));
    }
}
