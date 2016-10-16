package com.fcherchi.demos.infra;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Fernando Cherchi on 15/10/2016.
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {

        return "login";
    }

}
