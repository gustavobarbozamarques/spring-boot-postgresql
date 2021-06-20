package br.com.gustavobarbozamarques.mocks;

import br.com.gustavobarbozamarques.entities.Category;

public class CategoryMock {
    public static Category get() {
        return Category.builder()
                .id(1)
                .name("Category 1")
                .build();
    }
}
