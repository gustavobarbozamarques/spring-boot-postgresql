package br.com.gustavobarbozamarques.controllers;

import br.com.gustavobarbozamarques.dto.ProductDTO;
import br.com.gustavobarbozamarques.dto.SimpleProductDTO;
import br.com.gustavobarbozamarques.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.validation.Valid;

@Api(tags = "Product Catalog")
@RestController
@Validated
@RequestMapping(path = "/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("List products.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success")
    })
    public List<SimpleProductDTO> list() {
        return productService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Save new product.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success")
    })
    public void save(@Valid @RequestBody ProductDTO productDTO) {
        productService.save(productDTO);
    }
}
