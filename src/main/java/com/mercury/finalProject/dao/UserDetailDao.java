package com.mercury.finalProject.dao;

import com.mercury.finalProject.bean.User;
import com.mercury.finalProject.bean.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailDao extends JpaRepository<UserDetail, Integer> {

    UserDetail findByUser(User user);

}
