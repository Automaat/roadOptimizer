package com.hereforbeer.web.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDTO {
    private String firstname;
    private String lastname;
    private LocationDTO point;
}
