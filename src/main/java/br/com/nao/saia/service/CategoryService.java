package br.com.nao.saia.service;

import br.com.nao.saia.converter.CategoryConverter;
import br.com.nao.saia.converter.MerchantConverter;
import br.com.nao.saia.dto.CategoryDTO;
import br.com.nao.saia.exception.CategoryNotFoundException;
import br.com.nao.saia.model.Category;
import br.com.nao.saia.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Mono<CategoryDTO> findById(final UUID id) {
        return categoryRepository.findById(id)
                .map(CategoryConverter::fromDomainToDTO)
                .switchIfEmpty(Mono.error(new CategoryNotFoundException(id)));
    }

    public Flux<CategoryDTO> findAll() {
        return categoryRepository.findAll()
                .map(CategoryConverter::fromDomainToDTO);
    }

    public Mono<CategoryDTO> save(final CategoryDTO categoryDTO) {
        return Mono.just(categoryDTO)
                .map(CategoryConverter::fromDTOToDomain)
                .flatMap(categoryToBeSaved -> categoryRepository.save(categoryToBeSaved)
                        .then(Mono.just(CategoryConverter.fromDomainToDTO(categoryToBeSaved))));
    }

}
