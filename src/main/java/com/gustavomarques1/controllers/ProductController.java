package com.gustavomarques1.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Product Catalog")
@RestController
@Validated
@RequestMapping(path = "/v1/products")
public class ProductController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get all products")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success")
    })
    public String getAll(){
        return "ok";
    }
}
