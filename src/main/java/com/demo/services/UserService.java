package com.demo.services;

import com.demo.dto.UserRequestParam;
import com.demo.dto.UserResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<UserResponseDTO> saveNewUser(UserRequestParam userRequestParam);

    ResponseEntity<UserResponseDTO> updateUser(Long userId, UserRequestParam updatedUserRequestParam);

    ResponseEntity<Boolean> deleteUser(Long userId);

    ResponseEntity<UserResponseDTO> findUserById(Long userId);

    ResponseEntity<UserResponseDTO> findUserByEmail(String email);

    ResponseEntity<List<UserResponseDTO>> findAllUsers();


}
