package com.room911.Business;

import com.room911.dto.request.LoginRequestDTO;
import com.room911.dto.response.LoginResponseDTO;
import com.room911.entity.User;
import com.room911.service.CustomUserDetailsService;
import com.room911.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthBusiness {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
                )
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Credenciales incorrectas");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        final User user = userDetailsService.findByUsername(loginRequest.getUsername());

        String roleName = user.getRole() != null ? user.getRole().getRoleName() : "ROLE_USER";

        final String jwt = jwtUtil.generateToken(
            userDetails.getUsername(),
            roleName,
            user.getUserId()
        );

        return new LoginResponseDTO(jwt, user.getUsername(), roleName, user.getUserId());
    }
}
