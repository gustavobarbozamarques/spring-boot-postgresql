package com.gustavomarques1.services;

import com.gustavomarques1.mocks.ProductMock;
import com.gustavomarques1.model.response.GetAllProductsResponse;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProductServiceTest {

    @Test
    public void testGetAllProductResponseModelMapper() {
        var product = ProductMock.get();
        var getAllProductResponse = new ModelMapper().map(product, GetAllProductsResponse.class);
        assertEquals(product.getId(), getAllProductResponse.getId());
        assertEquals(product.getName(), getAllProductResponse.getName());
        assertEquals(product.getCategory().getId(), getAllProductResponse.getCategoryId());
        assertEquals(product.getCategory().getName(), getAllProductResponse.getCategoryName());
    }
}
