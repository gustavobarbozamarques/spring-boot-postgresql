package br.com.gustavobarbozamarques.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class ProductRequestDTO {
    @NotBlank(message = "Name cannot be blank.")
    private String name;

    @NotBlank(message = "Description cannot be blank.")
    private String description;

    @NotNull(message = "Price cannot be null.")
    @DecimalMin(value = "0.00", message = "Invalid price value.")
    private BigDecimal price;

    @NotNull(message = "CategoryId cannot be null.")
    @Min(value = 1, message = "Invalid categoryId value.")
    private Integer categoryId;
}
