package br.com.nao.saia.controller;

import br.com.nao.saia.dto.CityDTO;
import br.com.nao.saia.repository.CityRepository;
import br.com.nao.saia.service.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;

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
    public Flux<CityDTO> findByFilter(@RequestParam @NotBlank final String uf) {
        return cityService.findByUF(uf);
    }

    @GetMapping("/{id}")
    public Mono<CityDTO> findById(@PathVariable final Integer id) {
        return cityService.findById(id);
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
