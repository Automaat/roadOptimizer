package com.hereforbeer.web.dto;

import com.hereforbeer.domain.User;

public class DTOMapper {

    public static User parseUserFromDTO(UserDTO userDTO) {
        return User.builder()
                .firstname(userDTO.getFirstname())
                .lastname(userDTO.getLastname())
                .nick(userDTO.getNick())
                .password(userDTO.getPassword())
                .build();
    }
}
