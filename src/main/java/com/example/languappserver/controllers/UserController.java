package com.example.languappserver.controllers;

import com.example.languappserver.models.User;
import com.example.languappserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import lombok.*;

@RestController
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String home(){
        return "Hello dear friend!";
    }

    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public User register(@RequestBody User user) {
        user = new User(user.getMail(), user.getPass(), user.getPhone(), user.getName());
        return userService.saveUser(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody String mail, String password, String id) {
        mail = mail.substring(6, mail.indexOf("&"));
        User user = userService.findUserById(id);
        if (user == null) {
            return "Wrong mail";
        } else if (passwordEncoder.matches(password, user.getPass())) {
            return "Login successful";
        } else {
            return "Wrong password";
        }
    }
}
