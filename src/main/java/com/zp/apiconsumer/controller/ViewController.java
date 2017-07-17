package com.zp.apiconsumer.controller;

import com.zp.apiconsumer.security.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;


@Controller
public class ViewController {

    @GetMapping(value = { "/login" })
    public String login(@AuthenticationPrincipal UserPrincipal userPrincipal) {

        if (Optional.ofNullable(userPrincipal).isPresent()) {
            return "redirect:/convert";
        }

        return "login";
    }


    @GetMapping(value = { "/error" })
    public String error() {

        return "error";
    }
}
