package br.com.nao.saia.controller;

import br.com.nao.saia.delete.Estado;
import br.com.nao.saia.delete.Estados;
import br.com.nao.saia.dto.StateDTO;
import br.com.nao.saia.model.Merchant;
import br.com.nao.saia.repository.StateRepository;
import br.com.nao.saia.service.StateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Classe que armazena os endpoints de {@link Merchant} recebendo as requisicoes,
 * tratando e devolvendo os resultados
 *
 * @author Taynan Rezende
 * @since 22/03/2020
 */
@RestController
@RequestMapping("states")
public class StateController {

    private final StateService stateService;
    private final StateRepository stateRepository;

    public StateController(StateService stateService, StateRepository stateRepository) {
        this.stateService = stateService;
        this.stateRepository = stateRepository;
    }

    @GetMapping
    public Flux<StateDTO> findAll() {
        return stateService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<StateDTO> findById(@PathVariable final Integer id) {
        return stateService.findDTOById(id);
    }

    @PostMapping
    public Mono<StateDTO> save(@Valid @RequestBody final StateDTO stateDTO) {
        return stateService.save(stateDTO);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void doSomethingAfterStartup() throws IOException {
//        String json = String.join(" ",
//                Files.readAllLines(
//                        Paths.get("/home/isaiasneto/Documentos/Projects/nao-saia/back-end-2/nao-saia/src/main/resources/estados_202003221427.json"),
//                        StandardCharsets.UTF_8));
//
//        List<Estado> states = new ObjectMapper().readValue(json, Estados.class).getEstados();
//
//        Flux<StateDTO> stateFlux = Flux.fromIterable(states)
//                .map(state -> new StateDTO(state.getCodigoibge(), state.getNomeestado(), state.getSigla(), state.getNomepais()))
//                .flatMap(stateService::save);
//
//        stateFlux
//                .then(stateRepository.count())
//                .subscribe(count -> System.out.println("Adding " + count + " states to data seed."));
//
//    }

}
