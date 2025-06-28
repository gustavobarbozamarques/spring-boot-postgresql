package br.com.gustavobarbozamarques.controllers;

import br.com.gustavobarbozamarques.dto.CategoryDTO;
import br.com.gustavobarbozamarques.mocks.CategoryDTOMock;
import br.com.gustavobarbozamarques.services.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testListAllShouldReturnOkAndCategoryList() throws Exception {
        var categoryList = List.of(CategoryDTOMock.get());
        when(categoryService.listAll()).thenReturn(categoryList);

        mockMvc.perform(get("/v1/categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(categoryList.get(0).getId()));
    }

    @Test
    void testListAllShouldReturnOkAndEmptyList() throws Exception {
        when(categoryService.listAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/v1/categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void testListByIdShouldReturnOkWhenFound() throws Exception {
        var category = CategoryDTOMock.get();
        when(categoryService.listById(category.getId())).thenReturn(category);

        mockMvc.perform(get("/v1/categories/{id}", category.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(category.getId()));
    }

    @Test
    void testListByIdShouldReturnNotFoundWhenNotExists() throws Exception {
        int categoryId = 1;
        when(categoryService.listById(categoryId)).thenThrow(new EntityNotFoundException());

        mockMvc.perform(get("/v1/categories/{id}", categoryId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void testListByIdShouldReturnBadRequestWhenInvalidId() throws Exception {
        mockMvc.perform(get("/v1/categories/{id}", "invalid-id"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSaveShouldReturnCreatedWhenPayloadIsValid() throws Exception {
        var categoryDTO = CategoryDTOMock.get();
        when(categoryService.save(any(CategoryDTO.class))).thenReturn(categoryDTO);

        mockMvc.perform(post("/v1/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDTO)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(categoryDTO.getId()));
    }

    @Test
    void testUpdateShouldReturnOkWhenPayloadIsValid() throws Exception {
        int categoryId = 1;
        var categoryDTO = CategoryDTOMock.get();
        when(categoryService.update(eq(categoryId), any(CategoryDTO.class))).thenReturn(categoryDTO);

        mockMvc.perform(put("/v1/categories/{id}", categoryId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(categoryDTO.getId()));
    }

    @Test
    void testUpdateShouldReturnNotFoundWhenNotExists() throws Exception {
        int categoryId = 1;
        var categoryDTO = CategoryDTOMock.get();
        when(categoryService.update(eq(categoryId), any(CategoryDTO.class))).thenThrow(new EntityNotFoundException());

        mockMvc.perform(put("/v1/categories/{id}", categoryId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDTO)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteShouldReturnNoContentWhenSuccessful() throws Exception {
        int categoryId = 1;
        doNothing().when(categoryService).delete(categoryId);

        mockMvc.perform(delete("/v1/categories/{id}", categoryId))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteShouldReturnNotFoundWhenNotExists() throws Exception {
        int categoryId = 1;
        doThrow(new EntityNotFoundException()).when(categoryService).delete(categoryId);

        mockMvc.perform(delete("/v1/categories/{id}", categoryId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
