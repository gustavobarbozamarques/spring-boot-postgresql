package br.com.gustavobarbozamarques.services;

import br.com.gustavobarbozamarques.dto.ProductDTO;
import br.com.gustavobarbozamarques.dto.SimpleProductDTO;
import br.com.gustavobarbozamarques.entities.Product;
import br.com.gustavobarbozamarques.repositories.CategoryRepository;
import br.com.gustavobarbozamarques.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<SimpleProductDTO> getAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, SimpleProductDTO.class))
                .collect(Collectors.toList());
    }

    public void save(ProductDTO productDTO) {
        var category = categoryRepository
                .findById(productDTO.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "CategoryId not found."));

        var product = modelMapper.map(productDTO, Product.class);
        product.setCategory(category);
        productRepository.save(product);
    }
}
