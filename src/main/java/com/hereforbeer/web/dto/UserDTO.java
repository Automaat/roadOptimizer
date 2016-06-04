package com.hereforbeer.web.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String nick;
    private String firstName;
    private String lastName;
    private String password;
}
