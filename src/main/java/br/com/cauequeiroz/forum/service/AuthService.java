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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    public boolean validateJwt(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    public Long getUserId(String token) {
        String userId = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        return Long.parseLong(userId);
    }

    public void authenticate(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token;

        if (authHeader == null  || authHeader.isEmpty() || !authHeader.startsWith("Bearer ")) {
            token = null;
        } else {
            token = authHeader.substring(7);
        }

        if (validateJwt(token)) {
            Long userId = getUserId(token);
            User user = userRepository.findById(userId).get();
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
