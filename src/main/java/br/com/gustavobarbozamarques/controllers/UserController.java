package br.com.gustavobarbozamarques.controllers;

import br.com.gustavobarbozamarques.dto.UserRequestDTO;
import br.com.gustavobarbozamarques.dto.UserResponseDTO;
import br.com.gustavobarbozamarques.services.UserService;
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

@Api(tags = "User Catalog")
@RestController
@Validated
@RequestMapping(path = "/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get all users.")
    public List<UserResponseDTO> listAll() {
        return userService.listAll();
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get user by id.")
    public UserResponseDTO listById(
            @PathVariable("userId") @Min(value = 1, message = "Invalid userId value.") Integer userId
    ) {
        return userService.listById(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Save new user.")
    public UserResponseDTO save(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return userService.save(userRequestDTO);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Update user by id.")
    public UserResponseDTO update(
            @PathVariable("userId") @Min(value = 1, message = "Invalid userId value.") Integer userId,
            @Valid @RequestBody UserRequestDTO userRequestDTO
    ) {
        return userService.update(userId, userRequestDTO);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete user by id.")
    public void delete(
            @PathVariable("userId") @Min(value = 1, message = "Invalid userId value.") Integer userId
    ) {
        userService.delete(userId);
    }
}
