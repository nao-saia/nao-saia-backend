package br.com.nao.saia.service;

import br.com.nao.saia.converter.StateConverter;
import br.com.nao.saia.dto.StateDTO;
import br.com.nao.saia.exception.StateNotFoundException;
import br.com.nao.saia.model.State;
import br.com.nao.saia.repository.StateRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

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

    public Mono<StateDTO> save(StateDTO stateDTO) {
        return Mono.just(stateDTO)
                .map(StateConverter::fromDTOToDomain)
                .flatMap(stateToBeSaved -> stateRepository.save(stateToBeSaved)
                        .then(Mono.just(StateConverter.fromDomainToDTO(stateToBeSaved))));
    }

//    public Flux<StateDTO> saveAll(List<State> states) {
//        return stateRepository.saveAll(states)
//                .flatMap(answer -> {
//                    return states
//                            .findById(answer.getQuestionId())
//                            .flatMap(question -> {
//                                question.getAnswers().removeIf(ans -> Objects.equals(ans.getId(), answer.getId()));
//                                question.getAnswers().add(answer);
//                                return questionRepository.save(question);
//                            })
//                            .thenReturn(answer);
//                });
//    }

}
