package br.com.gustavobarbozamarques.controllers;

import br.com.gustavobarbozamarques.dto.UserRequestDTO;
import br.com.gustavobarbozamarques.dto.UserResponseDTO;
import br.com.gustavobarbozamarques.mocks.UserRequestDTOMock;
import br.com.gustavobarbozamarques.mocks.UserMock;
import br.com.gustavobarbozamarques.services.UserService;
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

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testListAllShouldReturnOkAndUserList() throws Exception {
        var userList = List.of(UserMock.get());
        when(userService.listAll()).thenReturn(userList.stream().map(UserResponseDTO::from).collect(java.util.stream.Collectors.toList()));

        mockMvc.perform(get("/v1/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(userList.get(0).getId()));
    }

    @Test
    void testListAllShouldReturnOkAndEmptyList() throws Exception {
        when(userService.listAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/v1/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void testListByIdShouldReturnOkWhenFound() throws Exception {
        var user = UserMock.get();
        when(userService.listById(user.getId())).thenReturn(UserResponseDTO.from(user));

        mockMvc.perform(get("/v1/users/{id}", user.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()));
    }

    @Test
    void testListByIdShouldReturnNotFoundWhenNotExists() throws Exception {
        int userId = 1;
        when(userService.listById(userId)).thenThrow(new EntityNotFoundException());

        mockMvc.perform(get("/v1/users/{id}", userId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void testListByIdShouldReturnBadRequestWhenInvalidId() throws Exception {
        mockMvc.perform(get("/v1/users/{id}", "invalid-id"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSaveShouldReturnCreatedWhenPayloadIsValid() throws Exception {
        var userRequestDTO = UserRequestDTOMock.get();
        var userResponseDTO = UserResponseDTO.from(UserMock.get());
        when(userService.save(any(UserRequestDTO.class))).thenReturn(userResponseDTO);

        mockMvc.perform(post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDTO)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(userResponseDTO.getId()));
    }

    @Test
    void testUpdateShouldReturnOkWhenPayloadIsValid() throws Exception {
        int userId = 1;
        var userRequestDTO = UserRequestDTOMock.get();
        var userResponseDTO = UserResponseDTO.from(UserMock.get());
        when(userService.update(eq(userId), any(UserRequestDTO.class))).thenReturn(userResponseDTO);

        mockMvc.perform(put("/v1/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userResponseDTO.getId()));
    }

    @Test
    void testUpdateShouldReturnNotFoundWhenNotExists() throws Exception {
        int userId = 1;
        var userRequestDTO = UserRequestDTOMock.get();
        when(userService.update(eq(userId), any(UserRequestDTO.class))).thenThrow(new EntityNotFoundException());

        mockMvc.perform(put("/v1/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDTO)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteShouldReturnNoContentWhenSuccessful() throws Exception {
        int userId = 1;
        doNothing().when(userService).delete(userId);

        mockMvc.perform(delete("/v1/users/{id}", userId))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteShouldReturnNotFoundWhenNotExists() throws Exception {
        int userId = 1;
        doThrow(new EntityNotFoundException()).when(userService).delete(userId);

        mockMvc.perform(delete("/v1/users/{id}", userId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
