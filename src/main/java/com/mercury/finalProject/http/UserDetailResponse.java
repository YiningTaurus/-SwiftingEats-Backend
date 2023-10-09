package com.mercury.finalProject.http;

import com.mercury.finalProject.bean.UserDetail;

public class UserDetailResponse extends Response{

    private UserDetail userDetail;

    public UserDetailResponse(boolean success, UserDetail userDetail) {
        super(success);
        this.userDetail = userDetail;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

}
