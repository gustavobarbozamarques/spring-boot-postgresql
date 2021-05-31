package com.gustavomarques1.mocks;

import com.gustavomarques1.entities.Category;
import com.gustavomarques1.entities.Product;
import com.gustavomarques1.entities.Tag;

import java.math.BigDecimal;
import java.util.List;

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
                .tags(List.of(
                        Tag.builder()
                                .id(11)
                                .name("tag 1")
                                .build()
                ))
                .build();
    }
}
