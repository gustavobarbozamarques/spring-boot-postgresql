package com.gustavomarques1.controllers;

import com.gustavomarques1.dto.SimpleProductDTO;
import com.gustavomarques1.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Product Catalog")
@RestController
@Validated
@RequestMapping(path = "/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("List products")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success")
    })
    public List<SimpleProductDTO> list(){
        return productService.getAll();
    }
}
