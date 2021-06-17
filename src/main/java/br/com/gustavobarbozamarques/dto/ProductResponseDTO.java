package br.com.gustavobarbozamarques.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductResponseDTO {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer categoryId;
}
