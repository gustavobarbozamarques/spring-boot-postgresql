package br.com.gustavobarbozamarques.services;

import br.com.gustavobarbozamarques.config.ModelMapperConfig;
import br.com.gustavobarbozamarques.mocks.ProductMock;
import br.com.gustavobarbozamarques.repositories.CategoryRepository;
import br.com.gustavobarbozamarques.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    public void setup() {
        var modelMapper = new ModelMapperConfig().modelMapper();
        productService = new ProductService(categoryRepository, productRepository, modelMapper);
    }

    @Test
    public void testGetAll() {
        var productsFromDatabase = List.of(ProductMock.get());
        when(productRepository.findAll()).thenReturn(productsFromDatabase);
        var products = productService.getAll();
        assertThat(products)
                .isNotEmpty()
                .hasSize(productsFromDatabase.size());
    }
}
