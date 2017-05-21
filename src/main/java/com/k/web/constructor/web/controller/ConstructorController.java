package com.k.web.constructor.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author a.kolenchenko
 * @since 19.05.17
 */
@Controller
public class ConstructorController {

    @GetMapping("/constructor")
    public ModelAndView getConstructor() {
        return new ModelAndView("constructor/constructor");
    }

}
