package com.library.controller;

import com.library.service.RentInfoService;
import com.library.service.RoleService;
import com.library.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserController {
    private final UserService userService;

    private final RoleService roleService;

    private final RentInfoService rentInfoService;

    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, RoleService roleService, RentInfoService rentInfoService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.rentInfoService = rentInfoService;
        this.passwordEncoder = passwordEncoder;
    }
}
