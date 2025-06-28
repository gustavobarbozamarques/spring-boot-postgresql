package br.com.gustavobarbozamarques.services;

import br.com.gustavobarbozamarques.dto.UserRequestDTO;
import br.com.gustavobarbozamarques.entities.User;
import br.com.gustavobarbozamarques.mocks.UserMock;
import br.com.gustavobarbozamarques.mocks.UserRequestDTOMock;
import br.com.gustavobarbozamarques.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testListAll() {
        var usersFromDatabase = List.of(UserMock.get());
        when(userRepository.findAll()).thenReturn(usersFromDatabase);

        var users = userService.listAll();

        assertThat(users)
                .isNotEmpty()
                .hasSize(usersFromDatabase.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testListByIdShouldReturnUserWhenFound() {
        var user = UserMock.get();
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        var result = userService.listById(1);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(user.getName());
        verify(userRepository, times(1)).findById(anyInt());
    }

    @Test
    void testListByIdShouldThrowExceptionWhenNotFound() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.listById(1));

        verify(userRepository, times(1)).findById(anyInt());
    }

    @Test
    void testSave() {
        var userToSave = UserMock.get();
        when(userRepository.save(any(User.class))).thenReturn(userToSave);

        var savedUser = userService.save(UserRequestDTOMock.get());

        assertThat(savedUser).isNotNull();
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateShouldUpdateSuccessfullyWhenUserExists() {
        var existingUser = UserMock.get();
        var userDetails = UserRequestDTOMock.get();
        userDetails.setName("Updated Name");

        when(userRepository.findById(anyInt())).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        var updatedUser = userService.update(1, userDetails);

        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getName()).isEqualTo("Updated Name");
        verify(userRepository, times(1)).findById(anyInt());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateShouldThrowExceptionWhenUserNotFound() {
        var userDetails = UserRequestDTOMock.get();
        userDetails.setName("Updated Name");

        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.update(1, userDetails));

        verify(userRepository, times(1)).findById(anyInt());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testDelete() {
        var user = UserMock.get();
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        doNothing().when(userRepository).deleteById(anyInt());
        userService.delete(1);
        verify(userRepository, times(1)).deleteById(anyInt());
    }
}
