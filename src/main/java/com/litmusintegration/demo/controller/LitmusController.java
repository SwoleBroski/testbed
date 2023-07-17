package com.litmusintegration.demo.controller;

import com.litmusintegration.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/litmus")
public class LitmusController {
    @Autowired
    public AuthenticationService authenticationService;

    @GetMapping("/get-auth-token")
    public void GetAuthToken() {
        authenticationService.refreshToken();
    }

}
