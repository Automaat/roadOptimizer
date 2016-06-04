package com.hereforbeer.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder(builderMethodName = "hiddenUserBuilder")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
@Document(collection = "users")
public class User {

    @Id
    private String id;

    @NotNull
    private String nick;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    private String password;

    public static User.UserBuilder builder(){
        return hiddenUserBuilder().id(UUID.randomUUID().toString());
    }
}
