package com.mercury.finalProject.controller;

import com.mercury.finalProject.http.Response;
import com.mercury.finalProject.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/checkLogin")
    public Response checkLogin(Authentication authentication) {
        return authService.checkLogin(authentication);
    }

}
