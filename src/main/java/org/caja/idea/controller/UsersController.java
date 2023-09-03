package org.caja.idea.controller;

import jakarta.validation.Valid;
import org.caja.idea.entity.dto.userDTO.AuthenticationRequest;
import org.caja.idea.entity.dto.userDTO.AuthenticationResponse;
import org.caja.idea.entity.dto.userDTO.UserDTO;
import org.caja.idea.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/authentication")
public class UsersController {
    @Autowired
    private IUserService service;

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAll(){
        return  ResponseEntity.ok(service.findAllUser());
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.login(request));
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.signup(request));
    }

}
