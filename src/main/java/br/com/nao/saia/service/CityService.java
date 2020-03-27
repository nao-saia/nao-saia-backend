package br.com.nao.saia.service;

import br.com.nao.saia.converter.CityConverter;
import br.com.nao.saia.dto.CityDTO;
import br.com.nao.saia.exception.CityNotFoundException;
import br.com.nao.saia.repository.CityRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final CityConverter cityConverter;

    public CityService(CityRepository cityRepository,
                       CityConverter cityConverter) {
        this.cityRepository = cityRepository;
        this.cityConverter = cityConverter;
    }

    public Mono<CityDTO> findById(final Integer id) {
        return cityRepository.findById(id)
                .map(cityConverter::fromDomainToDTO)
                .switchIfEmpty(Mono.error(new CityNotFoundException(id)));
    }

    public Flux<CityDTO> findByUF(final String uf) {
        return cityRepository.findByStateUfOrderByNameAsc(uf)
                .map(cityConverter::fromDomainToDTO);
    }

    public Mono<CityDTO> save(final CityDTO cityDTO) {
        return Mono.just(cityDTO)
                .map(cityConverter::fromDTOToDomain)
                .flatMap(cityToBeSaved -> cityRepository.save(cityToBeSaved)
                        .then(Mono.just(cityConverter.fromDomainToDTO(cityToBeSaved))));
    }

}
