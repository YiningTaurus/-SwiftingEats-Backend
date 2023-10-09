package com.mercury.finalProject.security.handler;

import com.mercury.finalProject.bean.User;
import com.mercury.finalProject.security.SecurityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationSuccessHandlerImpl extends SimpleUrlAuthenticationSuccessHandler {

//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) throws IOException, ServletException {
//        SecurityUtils.sendResponse(response, HttpServletResponse.SC_OK, "Login successfully.", null);
//    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        User userDetails = (User) authentication.getPrincipal();
        User user = new User(userDetails.getId(), userDetails.getUsername(), null, userDetails.getRestaurant(), userDetails.getRole());
        SecurityUtils.sendResponse(response, HttpServletResponse.SC_OK, "Login successfully.", null, user);
    }

}
