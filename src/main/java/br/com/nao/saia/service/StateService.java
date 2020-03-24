package br.com.nao.saia.service;

import br.com.nao.saia.converter.StateConverter;
import br.com.nao.saia.dto.StateDTO;
import br.com.nao.saia.exception.StateNotFoundException;
import br.com.nao.saia.model.State;
import br.com.nao.saia.repository.StateRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StateService {

    private final StateRepository stateRepository;

    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public Mono<State> findById(Integer id) {
        return stateRepository.findById(id)
                .switchIfEmpty(Mono.error(new StateNotFoundException(id)));
    }

    public Mono<StateDTO> findDTOById(Integer id) {
        return stateRepository.findById(id)
                .map(StateConverter::fromDomainToDTO)
                .switchIfEmpty(Mono.error(new StateNotFoundException(id)));
    }

    public Flux<StateDTO> findAll() {
        return stateRepository.findAll()
                .map(StateConverter::fromDomainToDTO);
    }

    public void save(StateDTO stateDTO) {
        State state = StateConverter.fromDTOToDomain(stateDTO);
        stateRepository.save(state);
    }

}
