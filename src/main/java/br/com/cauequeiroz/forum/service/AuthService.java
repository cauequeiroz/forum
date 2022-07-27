package br.com.cauequeiroz.forum.service;

import br.com.cauequeiroz.forum.model.User;
import br.com.cauequeiroz.forum.repository.UserRepository;
import br.com.cauequeiroz.forum.resource.request.LoginRequest;
import br.com.cauequeiroz.forum.resource.response.LoginResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Value("${forum.jwt.expiration}")
    private String expirationInMilliseconds;

    @Value("${forum.jwt.secret}")
    private String secret;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User do not exist.");
        }

        return user.get();
    }

    public String generateJwt(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Date creationDate = new Date();
        Date expirationDate = new Date(creationDate.getTime() + Long.parseLong(expirationInMilliseconds));

        return Jwts.builder()
                .setIssuer("Forum REST Api")
                .setSubject(user.getId().toString())
                .setIssuedAt(creationDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
