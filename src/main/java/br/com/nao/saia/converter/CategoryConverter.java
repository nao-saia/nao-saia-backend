package br.com.nao.saia.converter;

import br.com.nao.saia.dto.CategoryDTO;
import br.com.nao.saia.model.Category;

public final class CategoryConverter {

    private CategoryConverter() {
    }

    public static Category fromDTOToDomain(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setImage(categoryDTO.getImage());
        category.setHighlighted(categoryDTO.isHighlighted());
        return category;
    }

    public static CategoryDTO fromDomainToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setImage(category.getImage());
        categoryDTO.setHighlighted(category.isHighlighted());
        categoryDTO.setCreatedAt(category.getCreatedAt());
        categoryDTO.setUpdateAt(category.getUpdateAt());
        return categoryDTO;
    }

}
