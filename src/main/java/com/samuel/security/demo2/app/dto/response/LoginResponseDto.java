package com.samuel.security.demo2.app.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LoginResponseDto {

    private String username;


    private String password;


    private String token;

}
