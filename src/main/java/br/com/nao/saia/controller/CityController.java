package br.com.nao.saia.controller;

import br.com.nao.saia.dto.CityDTO;
import br.com.nao.saia.model.Merchant;
import br.com.nao.saia.service.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public Flux<CityDTO> findAll(@RequestParam(required = false) String uf) {
    	if (uf != null && !uf.isEmpty()) {
    		return cityService.findByUF(uf);
    	}
        return cityService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<CityDTO> findById(@PathVariable Integer id) {
        return cityService.findById(id);
    }
    
    @PostMapping
    public void save(@Valid @RequestBody CityDTO cityDTO) {
        cityService.save(cityDTO);
    }

    /*@EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = String.join(" ",
                Files.readAllLines(
                        Paths.get("/home/isaiasneto/Documentos/Projects/nao-saia/back-end-2/nao-saia/src/main/resources/cidades_202003221428.json"),
                        StandardCharsets.UTF_8)
        );

        Cidades cidades = objectMapper.readValue(json, Cidades.class);
        cidades.getCidades().stream()
                .map(cidade -> new CityDTO(cidade.getCodigoibge(), cidade.getNomecidade(), cidade.getIdestadoibge()))
                .forEach(cityService::save);
    }*/

}
