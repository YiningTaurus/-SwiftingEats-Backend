package com.mercury.finalProject.service;

import com.mercury.finalProject.bean.Restaurant;
import com.mercury.finalProject.bean.RestaurantAndManagerDto;
import com.mercury.finalProject.bean.User;
import com.mercury.finalProject.dao.RestaurantDao;
import com.mercury.finalProject.dao.UserDao;
import com.mercury.finalProject.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RestaurantDao restaurantDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAll() {
        return userDao.findAll();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Response saveRestaurantAndManager(RestaurantAndManagerDto restaurantAndManagerDto) {
        try {
            User newUser = restaurantAndManagerDto.getUser();
            Restaurant newRestaurant = restaurantAndManagerDto.getRestaurant();
            User existingUser = userDao.findByUsername(newUser.getUsername());
            if(existingUser != null){
                System.out.println("User already exists: " + existingUser);
                throw new RuntimeException("Username is already in use.");
            }else{
                Restaurant savedRestaurant = restaurantDao.save(newRestaurant);
                newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
                newUser.setRestaurant(savedRestaurant);
                userDao.save(newUser);
                return new Response(true, "Restaurant and manager saved successfully");
            }
        } catch (Exception e) {
            throw new RuntimeException("Restaurant & Manager saving failed. " + e.getMessage());
        }
    }

    public Response register(User user) {
        System.out.println("Registering user: " + user);
        User existingUser = userDao.findByUsername(user.getUsername());
        if (existingUser != null) {
            System.out.println("User already exists: " + existingUser);
            throw new RuntimeException("Username is already in use.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userDao.save(user);
        System.out.println("User registered successfully: " + savedUser);
        return new Response(true, "Successfully registered!");
    }

    public Response changePassword(User user, Authentication authentication) {
        User currentUser = userDao.findByUsername(authentication.getName());
        if (currentUser != null && (currentUser.getId() == user.getId() || isAdmin(authentication.getAuthorities()))) {
            User u = userDao.findByUsername(user.getUsername());
            u.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.save(u);
            return new Response(true, "Password changed successfully.");
        } else {
            return new Response(false, "Not authorized to change the password.");
        }
    }

    public boolean isAdmin(Collection<? extends GrantedAuthority> profiles) {
        boolean isAdmin = false;
        for(GrantedAuthority profile: profiles) {
            if(profile.getAuthority().equals("ROLE_WEB_ADMIN")) {
                isAdmin = true;
            }
        };
        return isAdmin;
    }

    public Response deleteUser(int id) {
        if(userDao.findById(id).get() != null) {
            userDao.deleteById(id);
            return new Response(true);
        }else {
            return new Response(false, "User is not found!");
        }
    }
}
