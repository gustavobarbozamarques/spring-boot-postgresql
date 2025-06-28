package br.com.gustavobarbozamarques.controllers;

import br.com.gustavobarbozamarques.dto.CategoryDTO;
import br.com.gustavobarbozamarques.services.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Api(tags = "Category Catalog")
@RestController
@Validated
@RequestMapping(path = "/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get all categories.")
    public List<CategoryDTO> listAll() {
        return categoryService.listAll();
    }

    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get category by id.")
    public CategoryDTO listById(
            @PathVariable("categoryId") @Min(value = 1, message = "Invalid categoryId value.") Integer categoryId
    ) {
        return categoryService.listById(categoryId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Save new category.")
    public CategoryDTO save(@Valid @RequestBody CategoryDTO categoryDTO) {
        return categoryService.save(categoryDTO);
    }

    @PutMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Update category by id.")
    public CategoryDTO update(
            @PathVariable("categoryId") @Min(value = 1, message = "Invalid categoryId value.") Integer categoryId,
            @Valid @RequestBody CategoryDTO categoryDTO
    ) {
        return categoryService.update(categoryId, categoryDTO);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete category by id.")
    public void delete(
            @PathVariable("categoryId") @Min(value = 1, message = "Invalid categoryId value.") Integer categoryId
    ) {
        categoryService.delete(categoryId);
    }
}
