package br.com.gustavobarbozamarques.services;

import br.com.gustavobarbozamarques.dto.ProductRequestDTO;
import br.com.gustavobarbozamarques.dto.ProductResponseDTO;
import br.com.gustavobarbozamarques.entities.Product;
import br.com.gustavobarbozamarques.repositories.CategoryRepository;
import br.com.gustavobarbozamarques.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<ProductResponseDTO> getAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductResponseDTO.class))
                .collect(Collectors.toList());
    }

    public List<ProductResponseDTO> getByCategory(Integer categoryId) {
        return productRepository.findByCategoryId(categoryId)
                .stream()
                .map(product -> modelMapper.map(product, ProductResponseDTO.class))
                .collect(Collectors.toList());
    }

    public ProductResponseDTO getById(Integer productId) {
        var product = productRepository
                .findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));

        var productResponseDTO = modelMapper.map(product, ProductResponseDTO.class);
        productResponseDTO.setCategoryId(product.getCategory().getId());
        return productResponseDTO;
    }


    public void save(Integer productId, ProductRequestDTO productRequestDTO) {
        var category = categoryRepository
                .findById(productRequestDTO.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid categoryId."));

        var product = modelMapper.map(productRequestDTO, Product.class);
        product.setId(productId);
        product.setCategory(category);
        product.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product);
    }

    public void delete(Integer productId) {
        var product = productRepository
                .findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));

        productRepository.delete(product);
    }
}
