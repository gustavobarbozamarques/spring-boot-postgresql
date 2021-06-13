package br.com.gustavobarbozamarques.controllers;

import br.com.gustavobarbozamarques.mocks.ProductDTOMock;
import br.com.gustavobarbozamarques.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnSuccessIfValid() throws Exception {
        var productDTO = ProductDTOMock.get();

        this.mockMvc
                .perform(
                        post("/v1/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(productDTO))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnBadRequestIfMissingName() throws Exception {
        var productDTO = ProductDTOMock.get();
        productDTO.setName(null);

        this.mockMvc
                .perform(
                        post("/v1/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(productDTO))
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnBadRequestIfMissingDescription() throws Exception {
        var productDTO = ProductDTOMock.get();
        productDTO.setDescription(null);

        this.mockMvc
                .perform(
                        post("/v1/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(productDTO))
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnBadRequestIfMissingPrice() throws Exception {
        var productDTO = ProductDTOMock.get();
        productDTO.setPrice(null);

        this.mockMvc
                .perform(
                        post("/v1/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(productDTO))
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnBadRequestIfInvalidPrice() throws Exception {
        var productDTO = ProductDTOMock.get();
        productDTO.setPrice(new BigDecimal("-1.00"));

        this.mockMvc
                .perform(
                        post("/v1/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(productDTO))
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
