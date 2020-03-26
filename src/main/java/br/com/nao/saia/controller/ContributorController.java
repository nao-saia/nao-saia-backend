package br.com.nao.saia.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nao.saia.dto.CategoryDTO;
import br.com.nao.saia.model.Contributor;
import br.com.nao.saia.service.ContributorService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("contributors")
public class ContributorController {

    private final ContributorService service;

    public ContributorController(ContributorService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<Contributor> findAll() {
        return service.findAll();
    }
    
    @GetMapping("/{id}")
    public Mono<Contributor> findById(@PathVariable final UUID id) {
        return service.findById(id);
    }

    @PostMapping
    public Mono<Contributor> save(@Valid @RequestBody final Contributor contributor) {
        return service.save(contributor);
    }
    

}
