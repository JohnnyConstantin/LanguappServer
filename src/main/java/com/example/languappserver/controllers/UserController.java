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
        user = new User(user.getMail(), passwordEncoder.encode(user.getPass()), user.getPhone(), user.getName());
        return userService.saveUser(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Integer login(@RequestBody User chil) {
        User user = userService.findUserById(chil.getMail());
        if (user == null) {
            return 0;
        } else if (passwordEncoder.matches(chil.getPass(), user.getPass())) {
            return 200;
        } else {
            return 100;
        }
    }
}
