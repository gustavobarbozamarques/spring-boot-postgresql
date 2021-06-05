package br.com.gustavobarbozamarques.services;

import br.com.gustavobarbozamarques.repositories.ProductRepository;
import br.com.gustavobarbozamarques.dto.SimpleProductDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<SimpleProductDTO> getAll() {
        //TODO: implementar paginação

        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, SimpleProductDTO.class))
                .collect(Collectors.toList());
    }
}
