package com.library.controller;

import com.library.service.BookService;
import com.library.service.RentInfoService;
import com.library.service.UserService;

public class RentInfoController {

    private final BookService bookService;

    private final RentInfoService rentInfoService;

    private final UserService userService;

    public RentInfoController(BookService bookService, RentInfoService rentInfoService, UserService userService) {
        this.bookService = bookService;
        this.rentInfoService = rentInfoService;
        this.userService = userService;
    }
}
