package com.samuel.security.demo2.app.service;

import com.samuel.security.demo2.app.dto.request.UserRequestDto;
import com.samuel.security.demo2.app.dto.response.LoginResponseDto;
import com.samuel.security.demo2.app.dto.response.RegistrationResponseDto;

public interface UserEntityService {

    RegistrationResponseDto userReg(UserRequestDto userRequestDto);

    LoginResponseDto userLogin(UserRequestDto userRequestDto);

}
