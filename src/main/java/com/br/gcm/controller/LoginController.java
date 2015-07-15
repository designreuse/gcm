package com.br.gcm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * http://www.mkyong.com/spring-security/spring-security-form-login-example/
 */

@Controller
public class LoginController {

    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login/form", method = RequestMethod.GET)
    public String loginForm() {
        return "login-layout";
    }

}