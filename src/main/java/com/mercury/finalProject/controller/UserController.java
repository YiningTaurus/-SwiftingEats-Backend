package com.mercury.finalProject.controller;

import com.mercury.finalProject.bean.Restaurant;
import com.mercury.finalProject.bean.RestaurantAndManagerDto;
import com.mercury.finalProject.bean.Role;
import com.mercury.finalProject.bean.User;
import com.mercury.finalProject.http.Response;
import com.mercury.finalProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_WEB_ADMIN')")
    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @PreAuthorize("permitAll()")
    @PostMapping
    public Response register(@RequestBody User user) {
        System.out.println(user);
        System.out.println(user.getRole());
        return userService.register(user);
    }

    @PostMapping("/addRestaurantManager")
    @PreAuthorize("hasRole('ROLE_WEB_ADMIN')")
    public Response createRestaurantAndManager(@RequestBody RestaurantAndManagerDto restaurantAndManagerDto) {
        return userService.saveRestaurantAndManager(restaurantAndManagerDto);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping
    public Response changePassword(@RequestBody User user, Authentication authentication) {
        return userService.changePassword(user, authentication);
    }

    @PreAuthorize("hasRole('ROLE_WEB_ADMIN')")
    @DeleteMapping("/{id}")
    public Response deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

}
