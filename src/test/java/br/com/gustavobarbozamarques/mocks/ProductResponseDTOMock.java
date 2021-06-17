package br.com.gustavobarbozamarques.mocks;

import br.com.gustavobarbozamarques.dto.ProductResponseDTO;

import java.math.BigDecimal;

public class ProductResponseDTOMock {
    public static ProductResponseDTO get() {
        return ProductResponseDTO.builder()
                .id(1)
                .name("Product 1")
                .description("Description 1")
                .price(new BigDecimal("9.99"))
                .categoryId(1)
                .build();
    }
}
