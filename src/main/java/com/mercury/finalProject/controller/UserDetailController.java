package com.mercury.finalProject.controller;

import com.mercury.finalProject.bean.UserDetail;
import com.mercury.finalProject.http.Response;
import com.mercury.finalProject.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userDetail")
public class UserDetailController {

    @Autowired
    private UserDetailService userDetailService;

    @GetMapping("/user/{userId}")
    public UserDetail getUserDetail(@PathVariable int userId) {
        return userDetailService.getUserDetail(userId);
    }

    @PostMapping
    public Response postUserDetail(@RequestBody UserDetail userDetail, Authentication authentication) {
        return userDetailService.addUserInfo(userDetail, authentication);
    }

    @PutMapping
    public Response updateUserDetail(@RequestBody UserDetail userDetail) {
        return userDetailService.updateUserInfo(userDetail);
    }

}
