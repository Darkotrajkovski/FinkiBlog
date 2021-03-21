package com.darkotrajkovski.finkiblog.controller;

import com.darkotrajkovski.finkiblog.exceptions.InvalidArgumentsException;
import com.darkotrajkovski.finkiblog.exceptions.PasswordsDoNotMatchException;
import com.darkotrajkovski.finkiblog.model.Role;
import com.darkotrajkovski.finkiblog.service.AuthService;
import com.darkotrajkovski.finkiblog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;
    private final UserService userService;

    public RegisterController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent", "register");
        return "master-template";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam Role role){
        try{
            userService.register(username, password, repeatedPassword, role);
            return "redirect:/login";
        } catch (PasswordsDoNotMatchException | InvalidArgumentsException exception){
            return "redirect:/register?error=" + exception.getMessage();
        }

    }
}
