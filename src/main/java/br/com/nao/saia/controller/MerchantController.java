package br.com.nao.saia.controller;

import br.com.nao.saia.dto.MerchantDTO;
import br.com.nao.saia.model.Merchant;
import br.com.nao.saia.service.MerchantService;
import br.com.nao.saia.service.PageSupport;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import java.util.UUID;

import static br.com.nao.saia.service.PageSupport.DEFAULT_PAGE_SIZE;
import static br.com.nao.saia.service.PageSupport.FIRST_PAGE_NUM;

/**
 * Classe que armazena os endpoints de {@link Merchant} recebendo as requisicoes,
 * tratando e devolvendo os resultados
 *
 * @author Taynan Rezende
 * @since 22/03/2020
 */
@RestController
@RequestMapping("merchants")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @GetMapping
    public Flux<MerchantDTO> findAll() {
        return merchantService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<MerchantDTO> findById(@PathVariable UUID id) {
        return merchantService.findById(id);
    }

    @GetMapping(path = "/category/{category}")
    public Mono<PageSupport<MerchantDTO>> findByCategory(@PathVariable String category,
                                                         @RequestParam(name = "page", defaultValue = FIRST_PAGE_NUM) Integer page,
                                                         @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) Integer size) {
        return merchantService.findByCategory(category, PageRequest.of(page, size));
    }

    @GetMapping(path = "/city/{city}")
    public Mono<PageSupport<MerchantDTO>> findByCity(@PathVariable String city,
                                                     @RequestParam(name = "page", defaultValue = FIRST_PAGE_NUM) Integer page,
                                                     @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) Integer size) {
        return merchantService.findByCity(city, PageRequest.of(page, size));
    }

    @GetMapping(path = "/state/{state}")
    public Mono<PageSupport<MerchantDTO>> findByUf(@PathVariable String state,
                                                   @RequestParam(name = "page", defaultValue = FIRST_PAGE_NUM) Integer page,
                                                   @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) Integer size) {
        return merchantService.findByState(state, PageRequest.of(page, size));
    }

    @GetMapping(path = "/location")
    public Mono<PageSupport<MerchantDTO>> findByLocation(@RequestParam("lat") double latitude,
                                                         @RequestParam("lon") double longitude,
                                                         @RequestParam(defaultValue = "10.0") double distance,
                                                         @RequestParam(name = "page", defaultValue = FIRST_PAGE_NUM) Integer page,
                                                         @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) Integer size) {
        return merchantService.findByLocation(latitude, longitude, distance, PageRequest.of(page, size));
    }

    @PostMapping
    public void save(@Valid @RequestBody MerchantDTO merchantDTO) {
        merchantService.save(merchantDTO);
    }

    @DeleteMapping("{id}")
    public Mono<Void> delete(@PathVariable UUID id) {
        return merchantService.deleteById(id);
    }

}
