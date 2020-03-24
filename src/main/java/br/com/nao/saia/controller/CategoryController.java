package br.com.nao.saia.controller;

import br.com.nao.saia.dto.CategoryDTO;
import br.com.nao.saia.model.Merchant;
import br.com.nao.saia.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

/**
 * Classe que armazena os endpoints de {@link Merchant} recebendo as requisicoes,
 * tratando e devolvendo os resultados
 *
 * @author Taynan Rezende
 * @since 22/03/2020
 */
@RestController
@RequestMapping("categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public Flux<CategoryDTO> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<CategoryDTO> findById(@PathVariable UUID id) {
        return categoryService.findById(id);
    }

    @PostMapping
    public void save(@Valid @RequestBody CategoryDTO cityDTO) {
        categoryService.save(cityDTO);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void doSomethingAfterStartup() throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = String.join(" ",
//                Files.readAllLines(
//                        Paths.get("/home/isaiasneto/Documentos/Projects/nao-saia/back-end-2/nao-saia/src/main/resources/categorias_202003221705.json"),
//                        StandardCharsets.UTF_8)
//        );
//
//        Categorias categorias = objectMapper.readValue(json, Categorias.class);
//        categorias.getCategorias().stream()
//                .map(CategoryDTO::new)
//                .forEach(categoryService::save);
//    }

}
