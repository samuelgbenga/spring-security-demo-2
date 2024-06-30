package com.samuel.security.demo2.app.controller;

import com.samuel.security.demo2.app.dto.request.UserRequestDto;
import com.samuel.security.demo2.app.dto.response.LoginResponseDto;
import com.samuel.security.demo2.app.dto.response.RegistrationResponseDto;
import com.samuel.security.demo2.app.service.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserEntityService userEntityService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDto> register(@RequestBody UserRequestDto userRequestDto) {
        RegistrationResponseDto response = userEntityService.userReg(userRequestDto);
        return ResponseEntity.ok(response);
    }

    // login
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody UserRequestDto userRequestDto) {
        LoginResponseDto response = userEntityService.userLogin(userRequestDto);
        return ResponseEntity.ok(response);
    }
}
