package br.com.nao.saia.controller;

import br.com.nao.saia.dto.MerchantDTO;
import br.com.nao.saia.service.MerchantService;
import br.com.nao.saia.service.PageSupport;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

import static br.com.nao.saia.service.PageSupport.DEFAULT_PAGE_SIZE;
import static br.com.nao.saia.service.PageSupport.FIRST_PAGE_NUM;

@RestController
@RequestMapping("merchants")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
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

    @GetMapping("/owner/{id}")
    public Mono<PageSupport<MerchantDTO>> findByUserId(@RequestParam(name = "page", defaultValue = FIRST_PAGE_NUM) Integer page,
                                                       @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                                                       @PathVariable("id") UUID userId) {
    	return merchantService.findByUserId(userId, PageRequest.of(page, size));
    }

    @PostMapping
    public Mono<MerchantDTO> save(@Valid @RequestBody final MerchantDTO merchantDTO) {
        return merchantService.save(merchantDTO);
    }

    @PatchMapping("{id}")
    public Mono<MerchantDTO> patch(@PathVariable final UUID id,
                                   @RequestBody final MerchantDTO merchantDTO) {
        return merchantService.patch(id, merchantDTO);
    }

    @PutMapping
    public Mono<MerchantDTO> update(@RequestBody final MerchantDTO merchantDTO) {
        return merchantService.update(merchantDTO);
    }

    @DeleteMapping("{id}")
    public Mono<MerchantDTO> delete(@PathVariable final UUID id) {
        return merchantService.deleteById(id);
    }
}
