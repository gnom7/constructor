package com.k.web.constructor.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@ControllerAdvice
public class ConstructorControllerAdvise {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConstructorControllerAdvise.class);

    @Autowired
    private Set<Validator> validators;

    @ExceptionHandler
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        LOGGER.error("Request: " + req.getRequestURL() + " raised " + ex);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("errors/error");
        return mav;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        validators.forEach(validator -> {
            if (validator.supports(binder.getTarget().getClass())) {
                binder.addValidators(validator);
            }
        });
    }

}
