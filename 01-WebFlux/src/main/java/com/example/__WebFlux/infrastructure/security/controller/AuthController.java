package com.example.__WebFlux.infrastructure.security.controller;

import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.__WebFlux.application.refreshToken.dtos.RefreshTokenDtoResponse;
import com.example.__WebFlux.application.refreshToken.services.RefreshTokenUseCaseImp;
import com.example.__WebFlux.application.user.dtos.request.LoginUserRequestDto;
import com.example.__WebFlux.application.user.dtos.response.LoginUserResponseDto;
import com.example.__WebFlux.infrastructure.security.CustomUserDetails;
import com.example.__WebFlux.infrastructure.security.jwt.JwtService;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final ReactiveUserDetailsService reactiveUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenUseCaseImp refreshTokenUseCaseImp;

    public AuthController(ReactiveUserDetailsService reactiveUserDetailsService, PasswordEncoder passwordEncoder,
            JwtService jwtService, RefreshTokenUseCaseImp refreshTokenUseCaseImp) {
        this.reactiveUserDetailsService = reactiveUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.refreshTokenUseCaseImp = refreshTokenUseCaseImp;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<LoginUserResponseDto>> login(@Valid @RequestBody LoginUserRequestDto body,
            ServerHttpResponse response) {

        String username = body.username();
        String password = body.password();

        return reactiveUserDetailsService.findByUsername(username)
                .flatMap(user -> {
                    if (!passwordEncoder.matches(password, user.getPassword())) {
                        return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
                    }
                    CustomUserDetails customUserDetails = (CustomUserDetails) user;
                    String user_id = customUserDetails.getUserId();
                    return jwtService.generateAccessToken(user)
                            .flatMap(access_token -> refreshTokenUseCaseImp.createRefreshToken(user_id)
                                    .map(refreshRaw -> {
                                        ResponseCookie cookie = ResponseCookie
                                                .from("refresh_token", refreshRaw)
                                                .httpOnly(true)
                                                .secure(true)
                                                .path("/api/auth/refresh")
                                                .maxAge(30 * 24 * 60 * 60)
                                                .sameSite("strict")
                                                .build();

                                        response.addCookie(cookie);

                                        LoginUserResponseDto access_response = new LoginUserResponseDto(access_token);
                                        return ResponseEntity.ok().body(access_response);

                                    }));

                });
    }

    @PostMapping("/refresh")
    public Mono<ResponseEntity<RefreshTokenDtoResponse>> refresh(ServerHttpRequest request,
            ServerHttpResponse response) {
        HttpCookie cookie = request.getCookies().getFirst("refresh_token");
        if (cookie == null)
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        String raw = cookie.getValue();
        return refreshTokenUseCaseImp.validateAndRotate(raw)
                .flatMap(newRefreshToken -> {
                    RefreshTokenDtoResponse refreshResponse = new RefreshTokenDtoResponse(newRefreshToken);
                    return Mono.just(ResponseEntity.ok().body(refreshResponse));
                })
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));

    }

    @PostMapping("/logout")
    public Mono<ResponseEntity<Void>> logout(ServerHttpRequest request, ServerHttpResponse response) {
        HttpCookie cookie = request.getCookies().getFirst("refresh_token");
        if (cookie == null)
            return Mono.just(ResponseEntity.noContent().build());
        String raw = cookie.getValue();
        return refreshTokenUseCaseImp.revoke(raw)
                .doOnSuccess(v -> {
                    ResponseCookie del = ResponseCookie
                            .from("refresh_token", "")
                            .path("/api/auth/refresh")
                            .maxAge(0)
                            .httpOnly(true)
                            .secure(true)
                            .build();
                    response.addCookie(del);

                }).thenReturn(ResponseEntity.noContent().build());
    }
}
