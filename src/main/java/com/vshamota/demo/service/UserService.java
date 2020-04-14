package com.vshamota.demo.service;

import com.vshamota.demo.domain.User;
import com.vshamota.demo.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserService {
//    private User user;

    public static boolean checkUserPrivileges(User user){
        Set<Role> userRoles = user.getRoles();
        for (Role role: userRoles) {
            if(role.toString().equals("ADMIN")){
                return true;
            }
        }
        return false;
    }
}
