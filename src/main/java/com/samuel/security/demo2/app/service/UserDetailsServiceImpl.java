//package com.samuel.security.demo2.app.service;
//
//import com.samuel.security.demo2.app.model.UserEntity;
//import com.samuel.security.demo2.app.model.UserEntityDetailsImpl;
//import com.samuel.security.demo2.app.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//   private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserEntity user = userRepository.findByEmail(username)
//                .orElseThrow(()-> new UsernameNotFoundException("User not Found"));
//
//    return new UserEntityDetailsImpl(user);
//    }
//}
