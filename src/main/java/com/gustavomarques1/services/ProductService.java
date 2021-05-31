package com.gustavomarques1.services;

import com.gustavomarques1.model.response.GetAllProductsResponse;
import com.gustavomarques1.repositories.ProductRepository;
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

    public List<GetAllProductsResponse> getAll() {
        //TODO: implementar paginação

        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, GetAllProductsResponse.class))
                .collect(Collectors.toList());
    }
}
