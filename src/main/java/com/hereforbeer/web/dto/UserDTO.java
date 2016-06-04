package com.hereforbeer.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDTO {
    private String id;
    private String nick;
    private String firstname;
    private String lastname;
    private String password;
}
