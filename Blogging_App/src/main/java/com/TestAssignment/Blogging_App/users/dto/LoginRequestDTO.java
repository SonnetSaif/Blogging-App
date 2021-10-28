package com.TestAssignment.Blogging_App.users.dto;

import com.google.gson.GsonBuilder;
import lombok.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDTO {

    @NotNull(message = "code must not be null")
    @NotEmpty(message = "code must not be empty")
    private String username;

    @NotNull(message = "name must not be null")
    @NotEmpty(message = "name must not be empty")
    private String password;;

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
