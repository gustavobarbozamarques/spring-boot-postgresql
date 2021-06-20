package br.com.gustavobarbozamarques.services;

import br.com.gustavobarbozamarques.config.ModelMapperConfig;
import br.com.gustavobarbozamarques.entities.Product;
import br.com.gustavobarbozamarques.mocks.CategoryMock;
import br.com.gustavobarbozamarques.mocks.ProductMock;
import br.com.gustavobarbozamarques.mocks.ProductRequestDTOMock;
import br.com.gustavobarbozamarques.repositories.CategoryRepository;
import br.com.gustavobarbozamarques.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

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
    void testGetAll() {
        var productsFromDatabase = List.of(ProductMock.get());
        when(productRepository.findAll()).thenReturn(productsFromDatabase);
        var products = productService.getAll();
        assertThat(products)
                .isNotEmpty()
                .hasSize(productsFromDatabase.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetByCategory() {
        var productsFromDatabase = List.of(ProductMock.get());
        when(productRepository.findByCategoryId(anyInt())).thenReturn(productsFromDatabase);
        var products = productService.getByCategory(1);
        assertThat(products)
                .isNotEmpty()
                .hasSize(productsFromDatabase.size());
        verify(productRepository, times(1)).findByCategoryId(anyInt());
    }

    @Test
    void testGetByIdShouldReturnProductIfFound() {
        var product = ProductMock.get();
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));
        var productDTO = productService.getById(1);
        assertThat(productDTO)
                .isNotNull();
        verify(productRepository, times(1)).findById(product.getId());
    }

    @Test
    void testGetByIdShouldThrowExceptionIfNotFound() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.ofNullable(null));
        assertThrows(ResponseStatusException.class, () -> {
            productService.getById(1);
        });
    }

    @Test
    void testSaveShouldSaveSuccessfullyIfValidCategory() {
        var category = CategoryMock.get();
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(category));
        productService.save(null, ProductRequestDTOMock.get());
        verify(categoryRepository, times(1)).findById(category.getId());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testSaveShouldThrowExceptionIfInvalidCategory() {
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.ofNullable(null));
        var request = ProductRequestDTOMock.get();
        assertThrows(ResponseStatusException.class, () -> {
            productService.save(null, request);
        });
        verify(categoryRepository, times(1)).findById(anyInt());
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void testDeleteShouldDeleteSuccessfullyIfProductExists() {
        var product = ProductMock.get();
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        productService.delete(product.getId());
        verify(productRepository, times(1)).findById(product.getId());
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    void testDeleteShouldThrowExceptionIfProductNotFound() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.ofNullable(null));
        assertThrows(ResponseStatusException.class, () -> {
            productService.delete(1);
        });
        verify(productRepository, times(1)).findById(anyInt());
        verify(productRepository, never()).delete(any(Product.class));
    }
}
