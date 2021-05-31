package com.gustavomarques1.model.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetAllProductsResponse {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer categoryId;
    private String categoryName;
}
