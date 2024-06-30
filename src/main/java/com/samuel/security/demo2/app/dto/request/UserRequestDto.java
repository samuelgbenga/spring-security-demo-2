package com.samuel.security.demo2.app.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDto {

    @NotBlank(message = "Username is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;
}
