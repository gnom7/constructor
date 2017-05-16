package com.k.web.constructor.web.controller;

import com.k.web.constructor.service.UserService;
import com.k.web.constructor.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ModelAndView getProfile() {
        UserDto logged = userService.logged();
        ModelAndView mav = new ModelAndView("user/profile");
        mav.addObject("user", logged);
        return mav;
    }

}
