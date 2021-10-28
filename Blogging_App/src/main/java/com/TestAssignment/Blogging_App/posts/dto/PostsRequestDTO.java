package com.TestAssignment.Blogging_App.posts.dto;

import com.google.gson.GsonBuilder;
import lombok.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostsRequestDTO {

    @NotNull(message = "code must not be null")
    @NotEmpty(message = "code must not be empty")
    private String title;

    @NotNull(message = "name must not be null")
    @NotEmpty(message = "name must not be empty")
    private String name;

    private String content;

    private String authorId;

    private String loginOid;

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
