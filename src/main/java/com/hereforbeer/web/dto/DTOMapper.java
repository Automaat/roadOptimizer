package com.hereforbeer.web.dto;

import com.hereforbeer.domain.User;

public class DTOMapper {

    public static User parseUserFromDTO(UserDTO userDTO) {
        return User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .nick(userDTO.getNick())
                .password(userDTO.getPassword())
                .build();
    }
}
