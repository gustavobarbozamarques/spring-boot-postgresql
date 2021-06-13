package br.com.gustavobarbozamarques.mocks;

import br.com.gustavobarbozamarques.dto.ProductDTO;

import java.math.BigDecimal;

public class ProductDTOMock {
    public static ProductDTO get() {
        return ProductDTO.builder()
                .name("Product 1")
                .description("Description 1")
                .price(BigDecimal.ONE)
                .categoryId(1)
                .build();
    }
}
