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
    public User getUser(@PathVariable String id) {
        return userService.getUser(id);
    }

    @RequestMapping(value = "/data", method = RequestMethod.POST)
    public String getMail(@RequestBody String mail){
        User user = userService.getUser(mail);
        return user.getMail()+"/"+user.getPhone()+"/"+user.getName();
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public User register(@RequestBody User user) {
        user = new User(user.getMail(), passwordEncoder.encode(user.getPass()), user.getPhone(), user.getName());
        return userService.saveUser(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody User chil) {
        User user = userService.findUserById(chil.getMail());
        if(passwordEncoder.matches(chil.getPass(), user.getPass())) {
            return "Login";
        } else if(user == null)
        {
            return "Mail";
        } else
        return "Pass";
    }
}
