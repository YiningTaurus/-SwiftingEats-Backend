package com.mercury.finalProject.service;

import com.mercury.finalProject.bean.User;
import com.mercury.finalProject.dao.UserDao;
import com.mercury.finalProject.http.AuthenticationSuccessResponse;
import com.mercury.finalProject.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserDao userDao;

    public Response checkLogin(Authentication authentication) {
        if (authentication != null) {
            User userDetails = (User) authentication.getPrincipal();
            return new AuthenticationSuccessResponse(true, 200, "Logged In!", userDao.findByUsername(userDetails.getUsername()));
        } else {
            return new Response(false);
        }
    }

}
