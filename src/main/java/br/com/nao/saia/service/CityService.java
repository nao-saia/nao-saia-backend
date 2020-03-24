package br.com.nao.saia.service;

import br.com.nao.saia.converter.CityConverter;
import br.com.nao.saia.dto.CityDTO;
import br.com.nao.saia.exception.CityNotFoundException;
import br.com.nao.saia.model.City;
import br.com.nao.saia.model.State;
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

    public Mono<CityDTO> findById(Integer id) {
        return cityRepository.findById(id)
                .map(cityConverter::fromDomainToDTO)
                .switchIfEmpty(Mono.error(new CityNotFoundException(id)));
    }
    
    public Flux<CityDTO> findByUF(String uf) {
    	return cityRepository.findByStateUf(uf)
    			.map(cityConverter::fromDomainToDTO);
    }

    public Flux<CityDTO> findAll() {
        return cityRepository.findAll()
                .map(cityConverter::fromDomainToDTO);
    }

    public void save(CityDTO cityDTO) {
        City city = cityConverter.fromDTOToDomain(cityDTO);
        cityRepository.save(city);
    }

}
