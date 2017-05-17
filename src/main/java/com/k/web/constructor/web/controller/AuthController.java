package com.k.web.constructor.web.controller;

import com.k.web.constructor.service.UserService;
import com.k.web.constructor.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ModelAndView getLogin() {
        return Objects.nonNull(userService.logged()) ? new ModelAndView("index") : new ModelAndView("auth/login");
    }

    @GetMapping("/register")
    public ModelAndView getSignUp() {
        ModelAndView mav = new ModelAndView("auth/register");
        mav.addObject("user", new UserDto());
        return mav;
    }

    @PostMapping("/register")
    public ModelAndView postSignUp(@ModelAttribute("user") UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("error/exception", bindingResult.getModel());
        }
        userService.register(userDto);
        return new ModelAndView("/");
    }

}
