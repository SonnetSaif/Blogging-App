package com.TestAssignment.Blogging_App.users.dto;

import com.google.gson.GsonBuilder;
import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDTO {

    private String oid;
    private String userName;
    private String role;
    private String message;

    @Override
    public String toString(){
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
