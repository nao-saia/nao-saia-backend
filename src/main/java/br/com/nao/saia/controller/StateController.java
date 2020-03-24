package br.com.nao.saia.controller;

import br.com.nao.saia.dto.StateDTO;
import br.com.nao.saia.model.Merchant;
import br.com.nao.saia.service.StateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

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

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping
    public Flux<StateDTO> findAll() {
        return stateService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<StateDTO> findById(@PathVariable Integer id) {
        return stateService.findDTOById(id);
    }

    @PostMapping
    public void save(@Valid @RequestBody StateDTO stateDTO) {
        stateService.save(stateDTO);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void doSomethingAfterStartup() throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = String.join(" ",
//                Files.readAllLines(
//                        Paths.get("/home/isaiasneto/Documentos/Projects/nao-saia/back-end-2/nao-saia/src/main/resources/estados_202003221427.json"),
//                        StandardCharsets.UTF_8)
//        );
//
//        Estados estados = objectMapper.readValue(json, Estados.class);
//        estados.getEstados().stream()
//                .map(estado -> new StateDTO(estado.getCodigoibge(), estado.getNomeestado(), estado.getSigla(), estado.getNomepais()))
//                .forEach(stateService::save);
//    }

}
