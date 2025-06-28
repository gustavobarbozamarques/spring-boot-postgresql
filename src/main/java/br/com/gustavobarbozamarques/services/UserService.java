package br.com.gustavobarbozamarques.services;

import br.com.gustavobarbozamarques.dto.UserRequestDTO;
import br.com.gustavobarbozamarques.dto.UserResponseDTO;
import br.com.gustavobarbozamarques.entities.User;
import br.com.gustavobarbozamarques.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> listAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO::from)
                .collect(Collectors.toList());
    }

    public UserResponseDTO listById(Integer id) {
        User user = findById(id);
        return UserResponseDTO.from(user);
    }

    public UserResponseDTO save(UserRequestDTO userRequestDTO) {
        User user = UserRequestDTO.from(userRequestDTO);
        // TODO: Implement password encoding before saving
        User savedUser = userRepository.save(user);
        return UserResponseDTO.from(savedUser);
    }

    public UserResponseDTO update(Integer id, UserRequestDTO userRequestDTO) {
        User user = findById(id);
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        // TODO: Implement password encoding before updating
        user.setPassword(userRequestDTO.getPassword());
        User updatedUser = userRepository.save(user);
        return UserResponseDTO.from(updatedUser);
    }

    public void delete(Integer id) {
        findById(id); // Check if user exists before deleting
        userRepository.deleteById(id);
    }

    private User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
    }
}
