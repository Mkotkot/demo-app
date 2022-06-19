package com.demo.services.impl;

import com.demo.dto.UserRequestParam;
import com.demo.dto.UserResponseDTO;
import com.demo.exceptions.DemoCustomServiceException;
import com.demo.mapper.UserMapper;
import com.demo.model.UserApp;
import com.demo.repositories.UserRepository;
import com.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public ResponseEntity<UserResponseDTO> saveNewUser(UserRequestParam userRequestParam) {
        validateUserRequestParam(userRequestParam);
        UserApp userApp = UserMapper.populateUserEntity(userRequestParam);
        return ResponseEntity.ok(UserMapper.populateUserResponseDTO(userRepository.save(userApp)));
    }

    private void validateUserRequestParam(UserRequestParam userRequestParam) {
        if (userRepository.existsByEmail(userRequestParam.getEmail())) {
            throw new DemoCustomServiceException("email is already exist", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (userRequestParam.getDateOfBirth().isAfter(LocalDate.now())) {
            throw new DemoCustomServiceException("Invalid Date of birth ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<UserResponseDTO> updateUser(Long userId, UserRequestParam updatedUserRequestParam) {
        userRepository.findById(userId).orElseThrow(() -> new DemoCustomServiceException("There is no user with id " + userId, HttpStatus.NOT_FOUND));
        UserApp userApp = UserMapper.populateUserEntity(updatedUserRequestParam);
        userApp.setId(userId);
        return ResponseEntity.ok(UserMapper.populateUserResponseDTO(userRepository.save(userApp)));
    }

    @Override
    public ResponseEntity<Boolean> deleteUser(Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new DemoCustomServiceException("There is no user with id " + userId, HttpStatus.NOT_FOUND));
        userRepository.deleteById(userId);
        return ResponseEntity.ok(true);
    }

    @Override
    public ResponseEntity<UserResponseDTO> findUserById(Long userId) {
        UserApp userApp = userRepository.findById(userId).orElseThrow(() -> new DemoCustomServiceException("There is no user with id " + userId, HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(UserMapper.populateUserResponseDTO(userApp));
    }

    @Override
    public ResponseEntity<UserResponseDTO> findUserByEmail(String email) {
        UserApp userApp = userRepository.findByEmail(email).orElseThrow(() -> new DemoCustomServiceException("There is no user with email " + email, HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(UserMapper.populateUserResponseDTO(userApp));
    }

    @Override
    public ResponseEntity<List<UserResponseDTO>> findAllUsers() {
        List<UserApp> userApps = userRepository.findAll();
        return ResponseEntity.ok(userApps.stream().map(UserMapper::populateUserResponseDTO).collect(Collectors.toList()));
    }
}
