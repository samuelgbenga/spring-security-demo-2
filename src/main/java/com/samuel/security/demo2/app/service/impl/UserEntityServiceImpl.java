package com.samuel.security.demo2.app.service.impl;

import com.samuel.security.demo2.app.dto.request.UserRequestDto;
import com.samuel.security.demo2.app.dto.response.LoginResponseDto;
import com.samuel.security.demo2.app.dto.response.RegistrationResponseDto;
import com.samuel.security.demo2.app.model.UserEntity;
import com.samuel.security.demo2.app.model.UserEntityDetailsImpl;
import com.samuel.security.demo2.app.repository.UserRepository;
import com.samuel.security.demo2.app.service.JwtService;
//import com.samuel.security.demo2.app.service.UserDetailsServiceImpl;
import com.samuel.security.demo2.app.service.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserEntityServiceImpl implements UserEntityService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    public RegistrationResponseDto userReg(UserRequestDto userRequestDto) {

        UserEntity user = UserEntity.builder()
                .email(userRequestDto.getEmail())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .build();

        userRepository.save(user);

        return RegistrationResponseDto.builder()
                .message("Registration Successful")
                .build();
    }


   //
    @Override
    public LoginResponseDto userLogin(UserRequestDto userRequestDto) {


            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userRequestDto.getEmail(), userRequestDto.getPassword()
            ));


        UserEntity user = userRepository.findByEmail(userRequestDto.getEmail()).orElseThrow();
        UserDetails userDetails = UserEntityDetailsImpl.builder().user(user).build();

        var jwtToken = jwtService.generateToken(userDetails);


        return LoginResponseDto.builder()
                .username(userRequestDto.getEmail())
                .token(jwtToken)
                .build();
    }
}
