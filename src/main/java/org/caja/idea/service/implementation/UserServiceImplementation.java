package org.caja.idea.service.implementation;

import org.caja.idea.configuration.jwt.JwtService;
import org.caja.idea.entity.dto.userDTO.AuthenticationRequest;
import org.caja.idea.entity.dto.userDTO.AuthenticationResponse;
import org.caja.idea.entity.dto.userDTO.AuthorizationRequest;
import org.caja.idea.entity.dto.userDTO.UserDTO;
import org.caja.idea.entity.models.Users;
import org.caja.idea.entity.role.Role;
import org.caja.idea.exception.EmailAlreadyExistsException;
import org.caja.idea.exception.UserNotFoundException;
import org.caja.idea.exception.UsernameAlreadyExistsException;
import org.caja.idea.repository.IUserRepository;
import org.caja.idea.service.interfaces.IUserService;
import org.caja.idea.utils.constants.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImplementation implements IUserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private IUserRepository repository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Override
    @Transactional
    public AuthenticationResponse login(AuthorizationRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword());
        authenticationManager.authenticate(authenticationToken);
        Users users = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException(Message.USERNAME_NOT_FOUND,404, HttpStatus.NOT_FOUND, LocalDateTime.now()));
        String jwt = jwtService.generateToken(users, jwtService.generateExtraClaims(users));
        return new AuthenticationResponse(Message.LOGIN_SUCCESSFULLY,jwt, HttpStatus.OK);
    }

    @Override
    @Transactional
    public AuthenticationResponse signup(AuthenticationRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        String email = request.getEmail();
        if(repository.existsByEmail(email)){
            throw new EmailAlreadyExistsException(Message.EMAIL_ALREADY_EXISTS,400, HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
        else if (repository.existsByUsername(username)){
            throw new UsernameAlreadyExistsException(Message.USERNAME_ALREADY_EXISTS,400, HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
        Users users = toUser(email, username, password);
        String jwt = jwtService.generateToken(users, jwtService.generateExtraClaims(users));
        return new AuthenticationResponse(Message.REGISTER_SUCCESSFULLY,jwt, HttpStatus.CREATED);
    }



    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findAllUser() {
        return repository.findAll()
                .stream()
                .map((dto) -> new UserDTO(dto.getId(),
                        dto.getUsername(),dto.getEmail(),
                        dto.getCreateAt(), dto.getUpdateAt()))
                .toList();
    }
    private Users toUser(String email, String username, String password) {
        Users users = new Users();
        users.setEmail(email);
        users.setUsername(username);
        users.setPassword(bCryptPasswordEncoder.encode(password));
        users.setRole(Role.USER);
        repository.save(users);
        return users;
    }
}
