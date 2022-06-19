package com.demo.controller;

import com.demo.dto.UserRequestParam;
import com.demo.dto.UserResponseDTO;
import com.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public HttpEntity<List<UserResponseDTO>> getAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping
    public HttpEntity<UserResponseDTO> saveNewUser(@RequestBody UserRequestParam userRequestParam) {
        return userService.saveNewUser(userRequestParam);
    }


    @GetMapping("/{Id}")
    public HttpEntity<UserResponseDTO> getUserById(@PathVariable("Id") Long userId) {
        return userService.findUserById(userId);
    }

    @GetMapping("/email/{email}")
    public HttpEntity<UserResponseDTO> getUserById(@PathVariable("email") String email) {
        return userService.findUserByEmail(email);
    }


    @PutMapping("/{Id}")
    public HttpEntity<UserResponseDTO> updateUser(@RequestBody UserRequestParam userRequestParam, @PathVariable("Id") Long userId) {
        return userService.updateUser(userId, userRequestParam);
    }

    @DeleteMapping("/{Id}")
    public HttpEntity<Boolean> deleteUserById(@PathVariable("Id") Long userId) {
        return userService.deleteUser(userId);
    }


}
