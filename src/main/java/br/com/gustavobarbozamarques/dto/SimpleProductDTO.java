package br.com.gustavobarbozamarques.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SimpleProductDTO {
    private Integer id;
    private String name;
    private BigDecimal price;
}
