package com.mercury.finalProject.service;

import com.mercury.finalProject.bean.User;
import com.mercury.finalProject.bean.UserDetail;
import com.mercury.finalProject.dao.UserDao;
import com.mercury.finalProject.dao.UserDetailDao;
import com.mercury.finalProject.http.Response;
import com.mercury.finalProject.http.UserDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserDetailDao userDetailDao;

    public Response addUserInfo(UserDetail userDetail, Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        userDetail.setUser(user);
        return new UserDetailResponse(true, userDetailDao.save(userDetail));
    }

    public Response updateUserInfo(UserDetail userDetail) {
        Optional<UserDetail> optionalUserDetail = userDetailDao.findById(userDetail.getId());
        if (optionalUserDetail.isPresent()) {
            UserDetail ud = optionalUserDetail.get();
            userDetail.setUser(ud.getUser());
            ud = userDetail;
            userDetailDao.save(ud);
            return new Response(true);
        } else {
            return new Response(false, "UserDetail not found");
        }
    }

    public UserDetail getUserDetail(int userId) {
        User user = userDao.findById(userId).orElse(null);
        return user != null ? userDetailDao.findByUser(user) : null;
    }

}
