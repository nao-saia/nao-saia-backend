package br.com.nao.saia.controller;

import static br.com.nao.saia.service.PageSupport.DEFAULT_PAGE_SIZE;
import static br.com.nao.saia.service.PageSupport.FIRST_PAGE_NUM;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.nao.saia.dto.MerchantDTO;
import br.com.nao.saia.service.MerchantService;
import br.com.nao.saia.service.PageSupport;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @GetMapping("{id}")
    public Mono<MerchantDTO> findById(@PathVariable final UUID id) {
        return merchantService.findById(id);
    }

    @GetMapping
    public Mono<PageSupport<MerchantDTO>> findByFilter(@RequestParam(required = false) String fantasyName,
                                                       @RequestParam(required = false) String category,
                                                       @RequestParam(required = false) String city,
                                                       @RequestParam(required = false) String state,
                                                       @RequestParam(name = "lat", required = false) Double latitude,
                                                       @RequestParam(name = "lon", required = false) Double longitude,
                                                       @RequestParam(defaultValue = "10.0") Double distance,
                                                       @RequestParam(name = "page", defaultValue = FIRST_PAGE_NUM) Integer page,
                                                       @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) Integer size) {
        return merchantService.findByFilter(fantasyName, category, city, state, latitude, longitude, distance, PageRequest.of(page, size));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")
    public Mono<MerchantDTO> save(@Valid @RequestBody final MerchantDTO merchantDTO) {
        return merchantService.save(merchantDTO);
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")
    public Mono<MerchantDTO> update(@PathVariable final UUID id,
                                    @RequestBody final MerchantDTO merchantDTO) {
        return merchantService.update(id, merchantDTO);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")
    public Mono<MerchantDTO> delete(@PathVariable final UUID id) {
        return merchantService.deleteById(id);
    }
}
