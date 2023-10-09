package com.mercury.finalProject.bean;

public enum Role {

    CUSTOMER(0),
    RESTAURANT_MANAGER(1),
    WEB_ADMIN(2),
    UNKOWN(3);

    private final int roleId;

    Role(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId(){
        return roleId;
    }

    public static Role fromValue(String role) {
        if (role.equals("CUSTOMER")){
            return Role.CUSTOMER;
        } else if (role.equals("RESTAURANT_MANAGER")){
            return Role.RESTAURANT_MANAGER;
        } else if (role.equals("WEB_ADMIN")){
            return Role.WEB_ADMIN;
        }
        return Role.UNKOWN;
    }
}
