package com.example.__WebFlux.infrastructure.security.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.__WebFlux.application.refreshToken.services.RefreshTokenUseCaseImp;
import com.example.__WebFlux.application.security.dtos.request.LoginUserRequestDto;
import com.example.__WebFlux.application.security.dtos.response.LoginUserResponseDto;
import com.example.__WebFlux.domain.refreshToken.services.RefreshTokenDomainService;
import com.example.__WebFlux.infrastructure.security.jwt.JwtService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final ReactiveUserDetailsService reactiveUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenUseCaseImp refreshTokenUseCaseImp;

    public AuthController(ReactiveUserDetailsService reactiveUserDetailsService, PasswordEncoder passwordEncoder, JwtService jwtService, RefreshTokenUseCaseImp refreshTokenUseCaseImp){
        this.reactiveUserDetailsService = reactiveUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.refreshTokenUseCaseImp = refreshTokenUseCaseImp;
    }
    
    public Mono<ResponseEntity<LoginUserResponseDto>> login(@RequestBody LoginUserRequestDto body, ServerHttpResponse response){

        String username = body.username();
        String password = body.password();

        // return reactiveUserDetailsService.findByUsername(username)
        //             .flatMap(user -> {
        //                 //comprobamos con bycrypt
        //                 if(!passwordEncoder.matches(password, user.getPassword())){
        //                     return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        //                 }

        //                 String user_id = user.getId();

        //                 return jwtService.generateAccessToken(user);
                                
        //             })
        return null;
    } 
}   
