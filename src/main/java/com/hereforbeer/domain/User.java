package com.hereforbeer.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;


@Data
@Builder(builderMethodName = "hiddenUserBuilder")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
@Document
public class User {

    @Id
    private String id;

    @NotNull
    private String nick;

    @NotNull
    private String lastname;

    @NotNull
    private String password;


}
