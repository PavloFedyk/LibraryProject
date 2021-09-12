package com.library.controller;

import com.library.entity.Author;
import com.library.entity.User;
import com.library.service.RentInfoService;
import com.library.service.RoleService;
import com.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final RoleService roleService;

    private final RentInfoService rentInfoService;

    private final PasswordEncoder passwordEncoder;


}
