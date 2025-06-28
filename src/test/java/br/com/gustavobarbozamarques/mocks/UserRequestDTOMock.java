package br.com.gustavobarbozamarques.mocks;

import br.com.gustavobarbozamarques.dto.UserRequestDTO;

public class UserRequestDTOMock {
    public static UserRequestDTO get() {
        return UserRequestDTO.builder()
                .name("Test User")
                .email("test@example.com")
                .password("password123")
                .build();
    }
}
