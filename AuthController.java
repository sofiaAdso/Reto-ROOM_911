package com.room911.controller;

import com.room911.Business.AuthBusiness;
import com.room911.dto.request.LoginRequestDTO;
import com.room911.dto.response.LoginResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthBusiness authBusiness;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        LoginResponseDTO response = authBusiness.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
