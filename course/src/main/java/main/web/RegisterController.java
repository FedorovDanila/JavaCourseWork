package main.web;

import main.entity.User;
import main.exception.InvalidLoginDataException;
import main.repository.UserRepository;
import main.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder pwdEncoder;

   @PostMapping()
    public void register(@RequestBody RegisterRequest registerRequest) {
        try {
            userRepository.save(new User(
                    registerRequest.getUserName(),
                    pwdEncoder.encode(registerRequest.getPassword()) ,
                    Collections.singletonList("ROLE_USER")));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exist");
        }
    }
}
