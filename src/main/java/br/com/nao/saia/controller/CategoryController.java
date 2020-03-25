package br.com.nao.saia.controller;

import br.com.nao.saia.dto.CategoryDTO;
import br.com.nao.saia.repository.CategoryRepository;
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

@RestController
@RequestMapping("categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryService categoryService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public Flux<CategoryDTO> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<CategoryDTO> findById(@PathVariable final UUID id) {
        return categoryService.findById(id);
    }

    @PostMapping
    public Mono<CategoryDTO> save(@Valid @RequestBody final CategoryDTO cityDTO) {
        return categoryService.save(cityDTO);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void doSomethingAfterStartup() throws IOException {
//        String json = String.join(" ",
//                Files.readAllLines(
//                        Paths.get("/home/isaiasneto/Documentos/Projects/nao-saia/back-end-2/nao-saia/src/main/resources/categorias_202003221705.json"),
//                        StandardCharsets.UTF_8));
//
//        List<String> categories = new ObjectMapper().readValue(json, Categorias.class).getCategorias();
//
//        Flux<CategoryDTO> cityFlux = Flux.fromIterable(categories)
//                .map(CategoryDTO::new)
//                .flatMap(categoryService::save);
//
//        cityFlux
//                .then(categoryRepository.count())
//                .subscribe(count -> System.out.println("Adding " + count + " categories to data seed."));
//    }

}
