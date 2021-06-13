package br.com.gustavobarbozamarques.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponseDTO {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer categoryId;
}
