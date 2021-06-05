package br.com.gustavobarbozamarques.mocks;

import br.com.gustavobarbozamarques.entities.Category;
import br.com.gustavobarbozamarques.entities.Product;

import java.math.BigDecimal;

public class ProductMock {
    public static Product get() {
        return Product.builder()
                .id(1)
                .name("product1")
                .description("description 1")
                .price(new BigDecimal("1.50"))
                .category(
                        Category.builder()
                                .id(5)
                                .name("category 1")
                                .build()
                )
                .build();
    }
}
