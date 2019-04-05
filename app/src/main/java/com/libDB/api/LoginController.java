package com.libDB.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping('/login')
    public String login(@RequestParam(name="id", required=true) String id, @RequestParam(name="password", required=true) String password, Model model) {
        return "ok";
}