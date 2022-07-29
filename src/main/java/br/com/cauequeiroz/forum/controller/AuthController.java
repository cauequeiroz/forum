package br.com.cauequeiroz.forum.controller;

import br.com.cauequeiroz.forum.resource.request.LoginRequest;
import br.com.cauequeiroz.forum.resource.response.LoginResponse;
import br.com.cauequeiroz.forum.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Profile("production")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken loginData =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

        try {
            String token = authService.generateJwt(authenticationManager.authenticate(loginData));
            return ResponseEntity.ok(new LoginResponse(token, "Bearer"));
        } catch (AuthenticationException error) {
            return ResponseEntity.badRequest().build();
        }
    }
}
