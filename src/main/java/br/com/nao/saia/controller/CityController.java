package br.com.nao.saia.controller;

import br.com.nao.saia.delete.Cidade;
import br.com.nao.saia.delete.Cidades;
import br.com.nao.saia.dto.CityDTO;
import br.com.nao.saia.model.Merchant;
import br.com.nao.saia.repository.CityRepository;
import br.com.nao.saia.service.CityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
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
@RequestMapping("cities")
public class CityController {

    private final CityService cityService;
    private final CityRepository cityRepository;

    public CityController(CityService cityService, CityRepository cityRepository) {
        this.cityService = cityService;
        this.cityRepository = cityRepository;
    }

    @GetMapping
    public Flux<CityDTO> findAll(@RequestParam(required = false) final String uf) {
    	if (uf != null && !uf.isEmpty()) {
    		return cityService.findByUF(uf);
    	}
        return cityService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<CityDTO> findById(@PathVariable final Integer id) {
        return cityService.findById(id);
    }

    @PostMapping
    public Mono<CityDTO> save(@Valid @RequestBody final CityDTO cityDTO) {
        return cityService.save(cityDTO);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void doSomethingAfterStartup() throws IOException {
//        String json = String.join(" ",
//                Files.readAllLines(
//                        Paths.get("/home/isaiasneto/Documentos/Projects/nao-saia/back-end-2/nao-saia/src/main/resources/cidades_202003221428.json"),
//                        StandardCharsets.UTF_8));
//
//        List<Cidade> cities = new ObjectMapper().readValue(json, Cidades.class).getCidades();
//
//        Flux<CityDTO> cityFlux = Flux.fromIterable(cities)
//                .map(city -> new CityDTO(city.getCodigoibge(), city.getNomecidade(), city.getIdestadoibge()))
//                .flatMap(cityService::save);
//
//        cityFlux
//                .then(cityRepository.count())
//                .subscribe(count -> System.out.println("Adding " + count + " cities to data seed."));
//    }

}
