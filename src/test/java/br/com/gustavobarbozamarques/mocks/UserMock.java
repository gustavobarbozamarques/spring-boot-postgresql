package br.com.gustavobarbozamarques.mocks;

import br.com.gustavobarbozamarques.entities.User;

public class UserMock {
    public static User get() {
        return User.builder()
                .id(1)
                .name("Test User")
                .email("test@example.com")
                .password("password123")
                .build();
    }
}
