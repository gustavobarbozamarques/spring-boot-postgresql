package br.com.gustavobarbozamarques.mocks;

import br.com.gustavobarbozamarques.dto.CategoryDTO;

public class CategoryDTOMock {
    public static CategoryDTO get() {
        return CategoryDTO.builder()
                .id(1)
                .name("Category Name")
                .build();
    }
}
