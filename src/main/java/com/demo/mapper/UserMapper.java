package com.demo.mapper;

import com.demo.dto.UserRequestParam;
import com.demo.dto.UserResponseDTO;
import com.demo.model.UserApp;

public final class UserMapper {
    public static UserApp populateUserEntity(UserRequestParam userRequestParam) {
        UserApp userApp = new UserApp();
        userApp.setFirstName(userRequestParam.getFirstName());
        userApp.setLastName(userRequestParam.getLastName());
        userApp.setDateOfBirth(userRequestParam.getDateOfBirth());
        userApp.setEmail(userRequestParam.getEmail());
        return userApp;
    }

    public static UserResponseDTO populateUserResponseDTO(UserApp userApp) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(userApp.getId());
        userResponseDTO.setFirstName(userApp.getFirstName());
        userResponseDTO.setLastName(userApp.getLastName());
        userResponseDTO.setDateOfBirth(userApp.getDateOfBirth());
        userResponseDTO.setEmail(userApp.getEmail());
        return userResponseDTO;
    }

}
